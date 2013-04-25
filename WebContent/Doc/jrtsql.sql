/*==============================================================*/
/* 如意彩渠道推广及用户数据分析统计脚本                         */
/* Created on:       2010-6-4 09:25:32                          */
/* modify:    修正了  沉默用户数 逃离用户数的统计规则           */
/* modify:    修正了  修正老用户无法统计的问题                  */
/* modify:    添加红名单过滤功能                                */
/* Created on:       2010-6-10 09:23:32                         */
/* modify:    充值用户数统计误差                                */
/* Created on:       2010-6-12 09:23:32                         */
/* modify:    充值用户数统计添加首页数                          */
/* Created on:       2010-6-22 10:49:26                         */
/* modify:    过滤用户状态 屏蔽禁用和赠送用户                   */
/* Created on:       2010-12-23 16:39:26                        */
/* modify:    pvtj增加uvnum的统计                               */
/*==============================================================*/

/*业务推广统计表 pvtj*/  
-- pvtj begin

--删除当次统计结果，防止插入重复数据
delete from jrtstatis.pvtj pvtj where pvtj.tjdate = to_timestamp(to_char(sysdate-1,'YYYY-MM-DD'),'YYYY-MM-DD'); 
--插入统计结果
insert into jrtstatis.pvtj(id,tjdate,ywid,channelid,province,uvnum,visitnum,regnum,paynum,percent)
--插入项
select jrtstatis.PVTJ_NO.NEXTVAL, to_timestamp(to_char(sysdate-1,'YYYY-MM-DD'),'YYYY-MM-DD') tjdate,
       h.ycode,h.ccode,'未知',
       nvl(m.uvuserno,0),nvl(k.pvuserno,0),nvl(h.reguserno,0),nvl(h.czuserno,0),nvl(h.reguserno/decode(k.pvuserno,0,1,k.pvuserno),0) percent  
from

--下面是统计结果

(  select e.cname,e.cid,e.yname,e.ccode,e.ycode,e.reguserno,f.czuserno 
   from
   (  select c.cname,c.cid,c.yname,c.ccode,c.ycode,d.reguserno 
      from 
      (  select a.code ccode,a.name cname,to_char(a.id) cid,b.code ycode,b.name yname 
         from jrtstatis.channel a 
         left join jrtstatis.yw b
         on  a.ywid=b.id
      ) c --业务和渠道表
      left join 
      (  select channel,count(*) reguserno
         from ( select decode(userinfo.channel,' ','1',userinfo.channel) channel,userinfo.regtime 
                from jrtsch.tuserinfo userinfo
                --过滤红名单
                WHERE NOT EXISTS 
                    ( select redtel.tel 
                      from jrtstatis.redtel redtel 
                      where redtel.tel = userinfo.mobileid
                    )
                    --过滤用户状态 屏蔽禁用和赠送用户
                    and userinfo.state = 1
              ) 
              --添加老用户统计 
         where -- regtime >= to_timestamp(to_char((sysdate-1),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
               -- and regtime < to_timestamp(to_char((sysdate),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
               to_char(regtime, 'YYYY-MM-DD') = to_char((sysdate - 1), 'YYYY-MM-DD')
         group by channel
      ) d --用户注册数表
      on c.cid=d.channel
   ) e --业务和用户注册表
   
   left join
   (  select channel, count(*) czuserno 
      from
      (  select distinct tran.userNo 
         from jrtsch.ttransaction tran 
         where type in (2,3,10)  --type in (2,3,10)表示交易类型
               -- and tran.plattime >= to_timestamp(to_char((sysdate-1),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS') 
               -- and tran.plattime < to_timestamp(to_char((sysdate),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
               and to_char(tran.plattime, 'YYYY-MM-DD') = to_char((sysdate - 1), 'YYYY-MM-DD')
               -- and tran.state = 1 //有充值行为用户包括 不成功的
               and NOT EXISTS 
                    ( 
                      select * --userinfo1.mobileid 
                      from ( select * from jrtsch.tuserinfo userinfo2
                             where EXISTS    (
                                                 select redtel1.tel 
                                                 from jrtstatis.redtel redtel1 
                                                 where redtel1.tel = userinfo2.mobileid
                                              )
                           ) userinfo1 
                      where tran.userno = userinfo1.userno
                    )
      ) a

      left join 
      (  select userNo,channel 
         from (select decode(channel,' ','1',channel) channel,userNo  from jrtsch.tuserinfo) --添加老用户统计  
      ) b
      on a.userno=b.userno 
      group by channel
   ) f --充值用户表,同一个用户充值多次只算一个用户
   on e.cid=f.channel
)  h 
   
left join 

(  select channelid, count(*) pvuserno 
   from jrtstatis.pvcount 
   where (type=1 or type=5) 
         -- and tsj >= to_timestamp(to_char((sysdate-1),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
         -- and tsj < to_timestamp(to_char(2010-11-30(sysdate),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
         and to_char(tsj, 'YYYY-MM-DD') = to_char((sysdate - 1), 'YYYY-MM-DD')
   group by channelid
) k --渠道访问统计表

on h.cid=k.channelid

left join
(
   select channelid, count(*) uvuserno
   from jrtstatis.pvcount
   where type=100 and to_char(tsj, 'YYYY-MM-DD') = to_char((sysdate - 1), 'YYYY-MM-DD')
   group by channelid
) m

on h.cid=m.channelid;
  
-- pvtj end    



/*充值方式统计表 paytj*/
-- paytj begin

--删除当次统计结果，防止插入重复数据
delete from jrtstatis.paytj where TJDATE=to_timestamp(to_char(sysdate-1,'YYYY-MM-DD'),'YYYY-MM-DD'); 
--插入统计结果
insert into jrtstatis.paytj(ID,TJDATE,YWID,CHANNELID,PAYTYPE,USERNUM,PAYMONEY) 
--插入项
select jrtstatis.PAYTJ_NO.NEXTVAL,to_timestamp(to_char(sysdate-1,'YYYY-MM-DD'),'YYYY-MM-DD'),
       a.ycode,a.ccode,nvl(b.payplatname,'未知'),nvl(b.czuserno,0),nvl(b.sumamt/100,0)
from 
(  select a.code ccode,a.name cname,to_char(a.id) cid,b.code ycode,b.name yname 
   from jrtstatis.channel a 
   left join jrtstatis.yw b
   on  a.ywid=b.id  
)  a --业务渠道表
  
left join 
(     
   select e.channel,f.payplatname,e.sumamt,e.czuserno 
   from 
   (  -----e begin
      select d.channel,bankid,sum(amt) sumamt, count(*) czuserno
      from
      (  ------c begin
         select userno,bankid,sum(amt) amt  
         from 
         (  
            select ttransactionid,nvl(amt,0)*blsign amt 
            from jrtsch.taccountdetail 
            where ttransactiontype in (2,3,10) 
               -- and state=1
               -- and plattime >= to_timestamp(to_char((sysdate-1),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
               -- and plattime < to_timestamp(to_char((sysdate),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
               and to_char(plattime, 'YYYY-MM-DD') = to_char((sysdate - 1), 'YYYY-MM-DD')
         )  b --用户充值表

         left join
         (  
              select tran.id,tran.plattime,tran.userNo,tran.bankid 
             --  bankid 银行标识，充值时填写银行端得返回信息，其它交易为空
             --  accesstype = '接入方式，B表示web，W表示wap，
             --  type 交易类型  2 银行卡充值；3 平台卡充值；10 点卡充值
            from jrtsch.ttransaction tran 
            where type in (2,3,10)  -- 2 银行卡充值；3 平台卡充值；10 点卡充值
                  and tran.state = 1
                  -- and tran.plattime >= to_timestamp(to_char((sysdate-1),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
                  -- and tran.plattime < to_timestamp(to_char((sysdate),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
                  -- 过滤红名单
                  and not EXISTS 
                    ( 
                      select userinfo1.mobileid 
                      from ( select * from jrtsch.tuserinfo userinfo2
                             where    EXISTS (
                                                 select redtel1.tel 
                                                 from jrtstatis.redtel redtel1 
                                                 where redtel1.tel = userinfo2.mobileid
                                              )
                           ) userinfo1 
                      where tran.userno = userinfo1.userno
                    )
         )  a --用户充值明细
         on a.id=b.ttransactionid
         group by bankid,userno 
           -------c end
       )  c --用户id 银行标示 金额
         
       left join 
 
       (  select userNo,channel 
          from (select decode(channel,' ','1',channel) channel,userNo  from jrtsch.tuserinfo) --添加老用户统计  
       )  d --用户所属渠道
       on c.userno=d.userno
       group by channel,bankid
         -----e end
    )  e
    left join
  
    (  select distinct a.payplatcode,a.payplatname 
       from jrtsch.tpaymode a
    )  f--TPAYMODE表，是数据字典表，翻译支付平台，和接入方式
    on e.bankid=f.payplatcode

)  b
  
on a.cid=b.channel;
--同一个用户充值多次，只算一次。同一个用户在多个支付平台支付，每次算一个用户。
-- paytj end   


/*购彩方式统计表 buytj*/
-- buytj begin

--删除当次统计结果，防止插入重复数据
delete from jrtstatis.buytj where TJDATE= to_timestamp(to_char(sysdate-1,'YYYY-MM-DD'),'YYYY-MM-DD');
--插入统计结果
insert into jrtstatis.buytj(ID,TJDATE,YWID,CHANNELID,BUYTYPE,USERNUM,BUYMONEY)
--插入项
select jrtstatis.BUYTJ_NO.NEXTVAL ID,to_timestamp(to_char(sysdate-1,'YYYY-MM-DD'),'YYYY-MM-DD') TJDATE,
       a.ycode,a.ccode,nvl(b.agencyname||b.lotname,'未知'),nvl(b.gcuserno,0),nvl(b.sumamt/100,0)
from 
 
(   select a.code ccode,a.name cname,to_char(a.id) cid,b.code ycode,b.name yname 
    from jrtstatis.channel a 
    left join jrtstatis.yw b
    on a.ywid=b.id
)   a --渠道信息表
left join 
(   select e.channel,e.agencyname,f.cname lotname,e.sumamt,e.gcuserno 
    from
    (  select c.channel,d.name agencyname,c.lotno,c.sumamt,c.gcuserno 
       from
       (  select b.channel,a.agencyno,a.lotno,sum(a.amt) sumamt,count(*) gcuserno 
          from 
          (  select userno,a.agencyno,a.lotno,sum(a.amt) amt 
             from 
             (select * from 
             jrtsch.TLot 
             union all
             select * from  
             jrtsch.tlottc
             )
             a
             where 
                  to_char(a.ordertime,'YYYY-MM-DD') = to_char((sysdate-1),'YYYY-MM-DD')
             /* a.ordertime >= to_timestamp(to_char((sysdate-1),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
                   and a.ordertime < to_timestamp(to_char((sysdate),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')*/
		   and a.state = 1
                   -- 过滤红名单
                   and NOT EXISTS 
                     ( 
                       select userinfo1.mobileid 
                       from ( select * from jrtsch.tuserinfo userinfo2
                              where    EXISTS (
                                                  select redtel1.tel 
                                                  from jrtstatis.redtel redtel1 
                                                  where redtel1.tel = userinfo2.mobileid
                                               )
                            ) userinfo1 
                       where a.userno = userinfo1.userno
                     )
             group by userno,agencyno,lotno
          )  a
          left join   
          (  select userNo,channel 
             from (select decode(channel,' ','1',channel) channel,userNo  from jrtsch.tuserinfo) --添加老用户统计  
          )  b  
          on a.userno=b.userno--用户购买彩票的渠道号，彩种，和金额
          group by b.channel,a.agencyno,a.lotno
       )  c
       left join  
       (  select agencyno,name 
          from jrtsch.tchannel
       )  d
       on c.agencyno=d.agencyno
    )  e--关联渠道表，翻译渠道名称
    left join
    (  select a.id lotno,a.cname 
       from jrtsch.tlottype a
    )  f--翻译彩种名称
    on e.lotno=f.lotno
)   b
on a.cid=b.channel;

--buytj end 购彩方式 同一个用户在多个渠道够不同彩种，每个渠道和彩种算一次。  

/*用户行为统计表 useractiontj*/

-- useractiontj begin
--删除当次统计结果，防止插入重复数据
delete from jrtstatis.useractiontj where TJDATE = to_timestamp(to_char(sysdate-1,'YYYY-MM-DD'),'YYYY-MM-DD');
--插入统计结果
insert into jrtstatis.useractiontj(ID,TJDATE,INDEXVN,POPVN,PAYVN,PAYMONEY,PSOFTN,LOGINN,GETMONEYN,BUYN,PAYN) 
values(jrtstatis.USERACTIONTJ_NO.NEXTVAL,to_timestamp(to_char(sysdate-1,'YYYY-MM-DD'),'YYYY-MM-DD'),
--插入项

--首页访问数
(       
    select count(*) syno 
    from jrtstatis.pvcount
    where type = 1--首页
          -- and tsj >= to_timestamp(to_char((sysdate-1),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
          -- and tsj < to_timestamp(to_char((sysdate),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
          and to_char(tsj, 'YYYY-MM-DD') = to_char((sysdate - 1), 'YYYY-MM-DD')
),

--推广访问数    
(   select count(*) tgno 
    from jrtstatis.pvcount
    where type=5--推广
          -- and tsj >= to_timestamp(to_char((sysdate-1),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
          -- and tsj < to_timestamp(to_char((sysdate),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
          and to_char(tsj, 'YYYY-MM-DD') = to_char((sysdate - 1), 'YYYY-MM-DD')
),

--充值行为的数量(成功数)
(   select count(*) 
    from jrtsch.ttransaction tran
    where type in (2,3,10)
          and tran.state = 1
          -- and tran.plattime >= to_timestamp(to_char((sysdate-1),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
          -- and tran.plattime < to_timestamp(to_char((sysdate),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
          and to_char(tran.plattime, 'YYYY-MM-DD') = to_char((sysdate - 1), 'YYYY-MM-DD')
          -- 过滤红名单
          and NOT EXISTS 
              ( 
                 select userinfo1.mobileid 
                 from ( select * from jrtsch.tuserinfo userinfo2
                        where     EXISTS (
                                            select redtel1.tel 
                                            from jrtstatis.redtel redtel1 
                                            where redtel1.tel = userinfo2.mobileid
                                         )
                       ) userinfo1 
                 where tran.userno = userinfo1.userno
              )
),

--充值总金额
nvl(
     (  select sum(nvl(tdetail.amt,0)*tdetail.blsign)/100 
        from jrtsch.taccountdetail tdetail
        where tdetail.ttransactiontype in (2,3,10) 
              -- and state=1
              -- and tdetail.plattime >= to_timestamp(to_char((sysdate-1),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
              -- and tdetail.plattime < to_timestamp(to_char((sysdate),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
              and to_char(tdetail.plattime, 'YYYY-MM-DD') = to_char((sysdate - 1), 'YYYY-MM-DD')
              -- 过滤红名单
              and NOT EXISTS (
                                select tran.userno
                                from ( select tr.* from jrtsch.ttransaction tr
                                       where    EXISTS 
                                             ( 
                                                select userinfo1.mobileid 
                                                from ( select * from jrtsch.tuserinfo userinfo2
                                                       where    EXISTS (
                                                                           select redtel1.tel 
                                                                           from jrtstatis.redtel redtel1 
                                                                           where redtel1.tel = userinfo2.mobileid
                                                                        )
                                                     ) userinfo1 
                                                where tr.userno = userinfo1.userno
                                             )
                                     ) tran  
                                where tdetail.ttransactionid = tran.id
                             )
      ),0
    ),

--客户端下载数web+wab 
(   select count(*) czno 
    from jrtstatis.pvcount
    where type in (3,4)--web+wab下载页面
          -- and tsj >= to_timestamp(to_char((sysdate-1),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
          -- and tsj < to_timestamp(to_char((sysdate),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
          and to_char(tsj, 'YYYY-MM-DD') = to_char((sysdate - 1), 'YYYY-MM-DD')
),

--登录用户数
(   select count(*) regno 
    from jrtsch.tuserinfo userinfo
    where -- userinfo.modtime >= to_timestamp(to_char((sysdate-1),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
          -- and userinfo.modtime < to_timestamp(to_char((sysdate),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
          to_char(userinfo.modtime, 'YYYY-MM-DD') = to_char((sysdate - 1), 'YYYY-MM-DD')
           --过滤红名单
          and NOT EXISTS 
                    ( select redtel.tel 
                      from jrtstatis.redtel redtel 
                      where redtel.tel = userinfo.mobileid
                    )
),

--提现用户数
(  select count(*) 
   from
       (  select distinct tran.userno 
          from jrtsch.ttransaction tran --distinct 同一个用户多次提现只算一个用户
          where tran.type=5  --type=5表示交易类型为提现
                and tran.state = 1
                -- and tran.plattime >= to_timestamp(to_char((sysdate-1),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
                -- and tran.plattime < to_timestamp(to_char((sysdate),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
                and to_char(tran.plattime, 'YYYY-MM-DD') = to_char((sysdate - 1), 'YYYY-MM-DD')
                -- 过滤红名单
                and NOT EXISTS 
                        ( 
                            select userinfo1.mobileid 
                            from ( select * from jrtsch.tuserinfo userinfo2
                                   where     EXISTS (
                                                       select redtel1.tel 
                                                       from jrtstatis.redtel redtel1 
                                                       where redtel1.tel = userinfo2.mobileid
                                                    )
                                  ) userinfo1 
                            where tran.userno = userinfo1.userno
                         )
        )
),
  
--投注成功用户数
(  select count(*)
   from
       (  select distinct tran.userno 
          from jrtsch.ttransaction tran
          where tran.type=1 --投注
                and tran.state=1
                -- and tran.plattime >= to_timestamp(to_char((sysdate-1),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
                -- and tran.plattime < to_timestamp(to_char((sysdate),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
                and to_char(tran.plattime, 'YYYY-MM-DD') = to_char((sysdate - 1), 'YYYY-MM-DD')
                -- 过滤红名单
                and NOT EXISTS 
                        ( 
                            select userinfo1.mobileid 
                            from ( select * from jrtsch.tuserinfo userinfo2
                                   where     EXISTS (
                                                       select redtel1.tel 
                                                       from jrtstatis.redtel redtel1 
                                                       where redtel1.tel = userinfo2.mobileid
                                                    )
                                  ) userinfo1 
                            where tran.userno = userinfo1.userno
                         )
       )
),

--充值行为的数量(总数)
(   select count(*) 
    from jrtsch.ttransaction tran
    where tran.type in (2,3,10)
          -- and tran.plattime >= to_timestamp(to_char((sysdate-1),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
          -- and tran.plattime < to_timestamp(to_char((sysdate),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
          and to_char(tran.plattime, 'YYYY-MM-DD') = to_char((sysdate - 1), 'YYYY-MM-DD')
          -- 过滤红名单
          and NOT EXISTS 
                  ( 
                     select userinfo1.mobileid 
                     from ( select * from jrtsch.tuserinfo userinfo2
                            where      EXISTS (
                                                select redtel1.tel 
                                                from jrtstatis.redtel redtel1 
                                                where redtel1.tel = userinfo2.mobileid
                                              )
                           ) userinfo1 
                      where tran.userno = userinfo1.userno
                   )
)

--充值用户数
/*
(  select count(*) 
   from
       (  select distinct userno 
          from jrtsch.ttransaction
          where type in (2,3,10)
                and plattime >= to_timestamp(to_char((sysdate-1),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
                and plattime < to_timestamp(to_char((sysdate),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
       )
)
*/

);
-- useractiontj end


/*用户分析统计表 useranalysety*/

-- useranalysety begin
--删除当次统计结果，防止插入重复数据
delete from jrtstatis.useranalysety where TJDATE = to_timestamp(to_char(sysdate-1,'YYYY-MM-DD'),'YYYY-MM-DD');
--插入统计结果
insert into jrtstatis.useranalysety(ID,REGNUM,SILENTNUM,ACTIVENUM,VIPNUM,ESCAPENUM,TJDATE) 
values(jrtstatis.USERANALYSETY_NO.NEXTVAL,

--插入项

--注册用户数
(   select count(*) regno  
    from jrtsch.tuserinfo userinfo
    where -- userinfo.regtime >= to_timestamp(to_char((sysdate-1),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
          -- and userinfo.regtime < to_timestamp(to_char((sysdate),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
          to_char(userinfo.regtime, 'YYYY-MM-DD') = to_char((sysdate - 1), 'YYYY-MM-DD')
          --过滤红名单
          and NOT EXISTS 
                  ( select redtel.tel 
                    from jrtstatis.redtel redtel 
                    where redtel.tel = userinfo.mobileid
                  )
          --过滤用户状态 屏蔽禁用和赠送用户
          and userinfo.state = 1
),

--沉默用户数 到今日正好1个月无访问记录，并无任何订购关系（账户余额少于3且不为零）的用户数 
(  select count(*) 
   from 
       (  select userinfo.userno 
          from jrtsch.tuserinfo userinfo 
          where userinfo.modtime
                between 
                   to_timestamp(to_char((sysdate-31),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
                and 
                   to_timestamp(to_char((sysdate-30),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
                and userinfo.userno not in(select userno from jrtsch.tsubscribe where state!=2)
                --过滤红名单
                and NOT EXISTS 
                        ( select redtel.tel 
                          from jrtstatis.redtel redtel 
                          where redtel.tel = userinfo.mobileid
                        )
        )  a
        
   left join 
       ( select userno,balance from jrtsch.taccountdetail )b
   on a.userno=b.userno
   where b.balance>0 and b.balance<300
),

--新增活跃用户数  本日有首次充值的用户数
(  select count(*) 
   from
       (  select distinct userinfo.userno 
          from jrtsch.tuserinfo userinfo
          where userinfo.userno not in (
                                 select userno 
                                 from jrtsch.ttransaction 
                                 where type in (2,3,10)
                                       and state=1
                                       and plattime < to_timestamp(to_char((sysdate-1),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
 
                               )--在昨天之前没有充值，或者充值未成功
 
                and userinfo.userno in (
                                 select userno 
                                 from jrtsch.ttransaction 
                                 where type in (2,3,10)
                                       and state=1
                                       -- and plattime >= to_timestamp(to_char((sysdate-1),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
                                       -- and plattime < to_timestamp(to_char((sysdate),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')--昨天有充值，并且充值成功                                   
                                       and to_char(plattime, 'YYYY-MM-DD') = to_char((sysdate - 1), 'YYYY-MM-DD')

                               )
                --过滤红名单
                and NOT EXISTS 
                        ( select redtel.tel 
                          from jrtstatis.redtel redtel 
                          where redtel.tel = userinfo.mobileid
                        )
         )
),


--vip用户数 10天内投注金额大于100元的用户数 
(   select count(*)
    from
        (   --select userno ,sum(amt) amt 
            --from jrtsch.taccountdetail 
            --where ttransactiontype in (2,3,10)
                 -- and state=1
                -- and plattime >= to_timestamp(to_char((sysdate-11),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
                -- and plattime < to_timestamp(to_char((sysdate-1),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
            -- group by userno
            
            select lot.userno,sum(lot.amt) amt 
            from jrtsch.TLot lot
            where lot.ordertime >= to_timestamp(to_char((sysdate-11),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
                  and lot.ordertime < to_timestamp(to_char((sysdate-1),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
                  -- 过滤红名单
                  and NOT EXISTS 
                     ( 
                       select userinfo1.mobileid 
                       from ( select * from jrtsch.tuserinfo userinfo2
                              where    EXISTS (
                                                  select redtel1.tel 
                                                  from jrtstatis.redtel redtel1 
                                                  where redtel1.tel = userinfo2.mobileid
                                               )
                            ) userinfo1 
                       where lot.userno = userinfo1.userno
                     )
            group by userno
         )  a
    where a.amt>10000
), 

--逃离用户数
--到今日正好1个月无访问记录，并无任何订购关系（账户余额小于1元） 
(   select count(*) 
    from 
         (
             select t.userno 
             from jrtsch.tuserinfo t
             where t.modtime
                   between 
                      to_timestamp(to_char((sysdate-31),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
                   and 
                      to_timestamp(to_char((sysdate-30),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
                   and t.userno not in
                                     (  select userno from jrtsch.tsubscribe where state!=2 )
                   --过滤红名单
                   and NOT EXISTS 
                        ( select redtel.tel 
                          from jrtstatis.redtel redtel 
                          where redtel.tel = t.mobileid
                        )
          )a
    left join 
          ( select userno,balance from jrtsch.taccountdetail ) b
    on a.userno=b.userno
    where b.balance < 100
),
 
to_timestamp(to_char(sysdate-1,'YYYY-MM-DD'),'YYYY-MM-DD')

)

-- useranalysety end

-- to_timestamp to_timestamp


