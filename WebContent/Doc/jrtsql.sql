/*==============================================================*/
/* ����������ƹ㼰�û����ݷ���ͳ�ƽű�                         */
/* Created on:       2010-6-4 09:25:32                          */
/* modify:    ������  ��Ĭ�û��� �����û�����ͳ�ƹ���           */
/* modify:    ������  �������û��޷�ͳ�Ƶ�����                  */
/* modify:    ��Ӻ��������˹���                                */
/* Created on:       2010-6-10 09:23:32                         */
/* modify:    ��ֵ�û���ͳ�����                                */
/* Created on:       2010-6-12 09:23:32                         */
/* modify:    ��ֵ�û���ͳ�������ҳ��                          */
/* Created on:       2010-6-22 10:49:26                         */
/* modify:    �����û�״̬ ���ν��ú������û�                   */
/* Created on:       2010-12-23 16:39:26                        */
/* modify:    pvtj����uvnum��ͳ��                               */
/*==============================================================*/

/*ҵ���ƹ�ͳ�Ʊ� pvtj*/  
-- pvtj begin

--ɾ������ͳ�ƽ������ֹ�����ظ�����
delete from jrtstatis.pvtj pvtj where pvtj.tjdate = to_timestamp(to_char(sysdate-1,'YYYY-MM-DD'),'YYYY-MM-DD'); 
--����ͳ�ƽ��
insert into jrtstatis.pvtj(id,tjdate,ywid,channelid,province,uvnum,visitnum,regnum,paynum,percent)
--������
select jrtstatis.PVTJ_NO.NEXTVAL, to_timestamp(to_char(sysdate-1,'YYYY-MM-DD'),'YYYY-MM-DD') tjdate,
       h.ycode,h.ccode,'δ֪',
       nvl(m.uvuserno,0),nvl(k.pvuserno,0),nvl(h.reguserno,0),nvl(h.czuserno,0),nvl(h.reguserno/decode(k.pvuserno,0,1,k.pvuserno),0) percent  
from

--������ͳ�ƽ��

(  select e.cname,e.cid,e.yname,e.ccode,e.ycode,e.reguserno,f.czuserno 
   from
   (  select c.cname,c.cid,c.yname,c.ccode,c.ycode,d.reguserno 
      from 
      (  select a.code ccode,a.name cname,to_char(a.id) cid,b.code ycode,b.name yname 
         from jrtstatis.channel a 
         left join jrtstatis.yw b
         on  a.ywid=b.id
      ) c --ҵ���������
      left join 
      (  select channel,count(*) reguserno
         from ( select decode(userinfo.channel,' ','1',userinfo.channel) channel,userinfo.regtime 
                from jrtsch.tuserinfo userinfo
                --���˺�����
                WHERE NOT EXISTS 
                    ( select redtel.tel 
                      from jrtstatis.redtel redtel 
                      where redtel.tel = userinfo.mobileid
                    )
                    --�����û�״̬ ���ν��ú������û�
                    and userinfo.state = 1
              ) 
              --������û�ͳ�� 
         where -- regtime >= to_timestamp(to_char((sysdate-1),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
               -- and regtime < to_timestamp(to_char((sysdate),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
               to_char(regtime, 'YYYY-MM-DD') = to_char((sysdate - 1), 'YYYY-MM-DD')
         group by channel
      ) d --�û�ע������
      on c.cid=d.channel
   ) e --ҵ����û�ע���
   
   left join
   (  select channel, count(*) czuserno 
      from
      (  select distinct tran.userNo 
         from jrtsch.ttransaction tran 
         where type in (2,3,10)  --type in (2,3,10)��ʾ��������
               -- and tran.plattime >= to_timestamp(to_char((sysdate-1),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS') 
               -- and tran.plattime < to_timestamp(to_char((sysdate),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
               and to_char(tran.plattime, 'YYYY-MM-DD') = to_char((sysdate - 1), 'YYYY-MM-DD')
               -- and tran.state = 1 //�г�ֵ��Ϊ�û����� ���ɹ���
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
         from (select decode(channel,' ','1',channel) channel,userNo  from jrtsch.tuserinfo) --������û�ͳ��  
      ) b
      on a.userno=b.userno 
      group by channel
   ) f --��ֵ�û���,ͬһ���û���ֵ���ֻ��һ���û�
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
) k --��������ͳ�Ʊ�

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



/*��ֵ��ʽͳ�Ʊ� paytj*/
-- paytj begin

--ɾ������ͳ�ƽ������ֹ�����ظ�����
delete from jrtstatis.paytj where TJDATE=to_timestamp(to_char(sysdate-1,'YYYY-MM-DD'),'YYYY-MM-DD'); 
--����ͳ�ƽ��
insert into jrtstatis.paytj(ID,TJDATE,YWID,CHANNELID,PAYTYPE,USERNUM,PAYMONEY) 
--������
select jrtstatis.PAYTJ_NO.NEXTVAL,to_timestamp(to_char(sysdate-1,'YYYY-MM-DD'),'YYYY-MM-DD'),
       a.ycode,a.ccode,nvl(b.payplatname,'δ֪'),nvl(b.czuserno,0),nvl(b.sumamt/100,0)
from 
(  select a.code ccode,a.name cname,to_char(a.id) cid,b.code ycode,b.name yname 
   from jrtstatis.channel a 
   left join jrtstatis.yw b
   on  a.ywid=b.id  
)  a --ҵ��������
  
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
         )  b --�û���ֵ��

         left join
         (  
              select tran.id,tran.plattime,tran.userNo,tran.bankid 
             --  bankid ���б�ʶ����ֵʱ��д���ж˵÷�����Ϣ����������Ϊ��
             --  accesstype = '���뷽ʽ��B��ʾweb��W��ʾwap��
             --  type ��������  2 ���п���ֵ��3 ƽ̨����ֵ��10 �㿨��ֵ
            from jrtsch.ttransaction tran 
            where type in (2,3,10)  -- 2 ���п���ֵ��3 ƽ̨����ֵ��10 �㿨��ֵ
                  and tran.state = 1
                  -- and tran.plattime >= to_timestamp(to_char((sysdate-1),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
                  -- and tran.plattime < to_timestamp(to_char((sysdate),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
                  -- ���˺�����
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
         )  a --�û���ֵ��ϸ
         on a.id=b.ttransactionid
         group by bankid,userno 
           -------c end
       )  c --�û�id ���б�ʾ ���
         
       left join 
 
       (  select userNo,channel 
          from (select decode(channel,' ','1',channel) channel,userNo  from jrtsch.tuserinfo) --������û�ͳ��  
       )  d --�û���������
       on c.userno=d.userno
       group by channel,bankid
         -----e end
    )  e
    left join
  
    (  select distinct a.payplatcode,a.payplatname 
       from jrtsch.tpaymode a
    )  f--TPAYMODE���������ֵ������֧��ƽ̨���ͽ��뷽ʽ
    on e.bankid=f.payplatcode

)  b
  
on a.cid=b.channel;
--ͬһ���û���ֵ��Σ�ֻ��һ�Ρ�ͬһ���û��ڶ��֧��ƽ̨֧����ÿ����һ���û���
-- paytj end   


/*���ʷ�ʽͳ�Ʊ� buytj*/
-- buytj begin

--ɾ������ͳ�ƽ������ֹ�����ظ�����
delete from jrtstatis.buytj where TJDATE= to_timestamp(to_char(sysdate-1,'YYYY-MM-DD'),'YYYY-MM-DD');
--����ͳ�ƽ��
insert into jrtstatis.buytj(ID,TJDATE,YWID,CHANNELID,BUYTYPE,USERNUM,BUYMONEY)
--������
select jrtstatis.BUYTJ_NO.NEXTVAL ID,to_timestamp(to_char(sysdate-1,'YYYY-MM-DD'),'YYYY-MM-DD') TJDATE,
       a.ycode,a.ccode,nvl(b.agencyname||b.lotname,'δ֪'),nvl(b.gcuserno,0),nvl(b.sumamt/100,0)
from 
 
(   select a.code ccode,a.name cname,to_char(a.id) cid,b.code ycode,b.name yname 
    from jrtstatis.channel a 
    left join jrtstatis.yw b
    on a.ywid=b.id
)   a --������Ϣ��
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
                   -- ���˺�����
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
             from (select decode(channel,' ','1',channel) channel,userNo  from jrtsch.tuserinfo) --������û�ͳ��  
          )  b  
          on a.userno=b.userno--�û������Ʊ�������ţ����֣��ͽ��
          group by b.channel,a.agencyno,a.lotno
       )  c
       left join  
       (  select agencyno,name 
          from jrtsch.tchannel
       )  d
       on c.agencyno=d.agencyno
    )  e--����������������������
    left join
    (  select a.id lotno,a.cname 
       from jrtsch.tlottype a
    )  f--�����������
    on e.lotno=f.lotno
)   b
on a.cid=b.channel;

--buytj end ���ʷ�ʽ ͬһ���û��ڶ����������ͬ���֣�ÿ�������Ͳ�����һ�Ρ�  

/*�û���Ϊͳ�Ʊ� useractiontj*/

-- useractiontj begin
--ɾ������ͳ�ƽ������ֹ�����ظ�����
delete from jrtstatis.useractiontj where TJDATE = to_timestamp(to_char(sysdate-1,'YYYY-MM-DD'),'YYYY-MM-DD');
--����ͳ�ƽ��
insert into jrtstatis.useractiontj(ID,TJDATE,INDEXVN,POPVN,PAYVN,PAYMONEY,PSOFTN,LOGINN,GETMONEYN,BUYN,PAYN) 
values(jrtstatis.USERACTIONTJ_NO.NEXTVAL,to_timestamp(to_char(sysdate-1,'YYYY-MM-DD'),'YYYY-MM-DD'),
--������

--��ҳ������
(       
    select count(*) syno 
    from jrtstatis.pvcount
    where type = 1--��ҳ
          -- and tsj >= to_timestamp(to_char((sysdate-1),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
          -- and tsj < to_timestamp(to_char((sysdate),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
          and to_char(tsj, 'YYYY-MM-DD') = to_char((sysdate - 1), 'YYYY-MM-DD')
),

--�ƹ������    
(   select count(*) tgno 
    from jrtstatis.pvcount
    where type=5--�ƹ�
          -- and tsj >= to_timestamp(to_char((sysdate-1),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
          -- and tsj < to_timestamp(to_char((sysdate),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
          and to_char(tsj, 'YYYY-MM-DD') = to_char((sysdate - 1), 'YYYY-MM-DD')
),

--��ֵ��Ϊ������(�ɹ���)
(   select count(*) 
    from jrtsch.ttransaction tran
    where type in (2,3,10)
          and tran.state = 1
          -- and tran.plattime >= to_timestamp(to_char((sysdate-1),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
          -- and tran.plattime < to_timestamp(to_char((sysdate),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
          and to_char(tran.plattime, 'YYYY-MM-DD') = to_char((sysdate - 1), 'YYYY-MM-DD')
          -- ���˺�����
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

--��ֵ�ܽ��
nvl(
     (  select sum(nvl(tdetail.amt,0)*tdetail.blsign)/100 
        from jrtsch.taccountdetail tdetail
        where tdetail.ttransactiontype in (2,3,10) 
              -- and state=1
              -- and tdetail.plattime >= to_timestamp(to_char((sysdate-1),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
              -- and tdetail.plattime < to_timestamp(to_char((sysdate),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
              and to_char(tdetail.plattime, 'YYYY-MM-DD') = to_char((sysdate - 1), 'YYYY-MM-DD')
              -- ���˺�����
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

--�ͻ���������web+wab 
(   select count(*) czno 
    from jrtstatis.pvcount
    where type in (3,4)--web+wab����ҳ��
          -- and tsj >= to_timestamp(to_char((sysdate-1),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
          -- and tsj < to_timestamp(to_char((sysdate),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
          and to_char(tsj, 'YYYY-MM-DD') = to_char((sysdate - 1), 'YYYY-MM-DD')
),

--��¼�û���
(   select count(*) regno 
    from jrtsch.tuserinfo userinfo
    where -- userinfo.modtime >= to_timestamp(to_char((sysdate-1),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
          -- and userinfo.modtime < to_timestamp(to_char((sysdate),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
          to_char(userinfo.modtime, 'YYYY-MM-DD') = to_char((sysdate - 1), 'YYYY-MM-DD')
           --���˺�����
          and NOT EXISTS 
                    ( select redtel.tel 
                      from jrtstatis.redtel redtel 
                      where redtel.tel = userinfo.mobileid
                    )
),

--�����û���
(  select count(*) 
   from
       (  select distinct tran.userno 
          from jrtsch.ttransaction tran --distinct ͬһ���û��������ֻ��һ���û�
          where tran.type=5  --type=5��ʾ��������Ϊ����
                and tran.state = 1
                -- and tran.plattime >= to_timestamp(to_char((sysdate-1),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
                -- and tran.plattime < to_timestamp(to_char((sysdate),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
                and to_char(tran.plattime, 'YYYY-MM-DD') = to_char((sysdate - 1), 'YYYY-MM-DD')
                -- ���˺�����
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
  
--Ͷע�ɹ��û���
(  select count(*)
   from
       (  select distinct tran.userno 
          from jrtsch.ttransaction tran
          where tran.type=1 --Ͷע
                and tran.state=1
                -- and tran.plattime >= to_timestamp(to_char((sysdate-1),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
                -- and tran.plattime < to_timestamp(to_char((sysdate),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
                and to_char(tran.plattime, 'YYYY-MM-DD') = to_char((sysdate - 1), 'YYYY-MM-DD')
                -- ���˺�����
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

--��ֵ��Ϊ������(����)
(   select count(*) 
    from jrtsch.ttransaction tran
    where tran.type in (2,3,10)
          -- and tran.plattime >= to_timestamp(to_char((sysdate-1),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
          -- and tran.plattime < to_timestamp(to_char((sysdate),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
          and to_char(tran.plattime, 'YYYY-MM-DD') = to_char((sysdate - 1), 'YYYY-MM-DD')
          -- ���˺�����
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

--��ֵ�û���
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


/*�û�����ͳ�Ʊ� useranalysety*/

-- useranalysety begin
--ɾ������ͳ�ƽ������ֹ�����ظ�����
delete from jrtstatis.useranalysety where TJDATE = to_timestamp(to_char(sysdate-1,'YYYY-MM-DD'),'YYYY-MM-DD');
--����ͳ�ƽ��
insert into jrtstatis.useranalysety(ID,REGNUM,SILENTNUM,ACTIVENUM,VIPNUM,ESCAPENUM,TJDATE) 
values(jrtstatis.USERANALYSETY_NO.NEXTVAL,

--������

--ע���û���
(   select count(*) regno  
    from jrtsch.tuserinfo userinfo
    where -- userinfo.regtime >= to_timestamp(to_char((sysdate-1),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
          -- and userinfo.regtime < to_timestamp(to_char((sysdate),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
          to_char(userinfo.regtime, 'YYYY-MM-DD') = to_char((sysdate - 1), 'YYYY-MM-DD')
          --���˺�����
          and NOT EXISTS 
                  ( select redtel.tel 
                    from jrtstatis.redtel redtel 
                    where redtel.tel = userinfo.mobileid
                  )
          --�����û�״̬ ���ν��ú������û�
          and userinfo.state = 1
),

--��Ĭ�û��� ����������1�����޷��ʼ�¼�������κζ�����ϵ���˻��������3�Ҳ�Ϊ�㣩���û��� 
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
                --���˺�����
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

--������Ծ�û���  �������״γ�ֵ���û���
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
 
                               )--������֮ǰû�г�ֵ�����߳�ֵδ�ɹ�
 
                and userinfo.userno in (
                                 select userno 
                                 from jrtsch.ttransaction 
                                 where type in (2,3,10)
                                       and state=1
                                       -- and plattime >= to_timestamp(to_char((sysdate-1),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')
                                       -- and plattime < to_timestamp(to_char((sysdate),'YYYY-MM-DD')||' 00:00:00','YYYY-MM-DD HH24:MI:SS')--�����г�ֵ�����ҳ�ֵ�ɹ�                                   
                                       and to_char(plattime, 'YYYY-MM-DD') = to_char((sysdate - 1), 'YYYY-MM-DD')

                               )
                --���˺�����
                and NOT EXISTS 
                        ( select redtel.tel 
                          from jrtstatis.redtel redtel 
                          where redtel.tel = userinfo.mobileid
                        )
         )
),


--vip�û��� 10����Ͷע������100Ԫ���û��� 
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
                  -- ���˺�����
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

--�����û���
--����������1�����޷��ʼ�¼�������κζ�����ϵ���˻����С��1Ԫ�� 
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
                   --���˺�����
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


