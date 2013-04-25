
function getRootPath(){
var strFullPath=window.document.location.href;
var strPath=window.document.location.pathname;
var pos=strFullPath.indexOf(strPath);
var prePath=strFullPath.substring(0,pos);
var postPath=strPath.substring(0,strPath.substr(1).indexOf('/')+1);
return(prePath+postPath);
}


// 导航栏配置文件
var outlookbar=new outlook();
var t;
t=outlookbar.addtitle('系统管理','系统管理',1)
outlookbar.additem('购彩统计',t,'buylist.jsp')


t=outlookbar.addtitle('业务管理','业务管理',1)
outlookbar.additem('业务管理',t,'yewu/listyw.do')

t=outlookbar.addtitle('渠道管理','渠道管理',1)
outlookbar.additem('渠道管理',t,'channel/listchannel.do')
outlookbar.additem('渠道分配',t,'channel/listusercfg.do')

t=outlookbar.addtitle('数据统计','数据统计',1)
outlookbar.additem('业务推广PV',t,'tj/pvtj.do')
outlookbar.additem('充值方式',t,'tj/paytj.do')
outlookbar.additem('购彩方式',t,'tj/buytj.do')
outlookbar.additem('用户行为',t,'tj/useractiontj.do')
outlookbar.additem('用户分析',t,'tj/useranalysety.do')

t=outlookbar.addtitle('用户查询','用户查询',1)
outlookbar.additem('用户详情',t,'userinfo/finduserdt.do')

t=outlookbar.addtitle('推广合作','推广合作',1)
outlookbar.additem('业务推广PV',t,'hezuo/pvlist.do')
outlookbar.additem('购彩统计',t,'hezuo/buylist.do')


t=outlookbar.addtitle('修改密码','修改密码',1)
outlookbar.additem('修改密码',t,'pass/userinfo.jsp')

t=outlookbar.addtitle('帮助中心','帮助中心',1)
outlookbar.additem('帮助中心',t,'help/index.jsp')