/**
 *	Title:			common.js
 *	Description:	�ṩͨ��javascript����
 *	Date:			2002/12/12
 *	Author:			����
 *	Version:		1.0
 */

/**
 * �û�����󳤶�
 */
var maxUserNameLength = 15;

/**
 * �û�����С����
 */
var minUserNameLength = 3;

/**
 * ������󳤶�
 */
var maxPasswordLength = 15;

/**
 * ������С����
 */
var minPasswordLength = 6;


function checkUserName (userName) {

	var validString = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_";

	var valid = true;

		if (userName.length > maxUserNameLength || userName.length < minUserNameLength) {
		    valid = false;
			return;
		} else {
		    if( "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_".indexOf(userName.charAt(0)) == -1 ){
					valid = false;
					return;				
			}			
			for (var i = 0; i < userName.length; i++) {//��֤�û����ÿһλ�Ƿ�������,��ĸ�����»���
				if (validString.indexOf(userName.charAt(i)) == -1) {//���������һλ���Ϸ�,��ô����false
					valid = false;
					break;
					return;
				}
			}
		}
	//������֤���
	return valid;
}

//=================================================================


function checkEmailAddress (emailAddress) {

	var validString = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_.@";

	var valid = true;

	var positionOfAT ;

	var positionOfFirstDot ;

	var positionOfLastDot ;

	var lengthOfEmailAddress ;


   
	if (emailAddress == "") {
	    valid = false;
		return;
	} else {
	    positionOfAT = emailAddress.lastIndexOf("@");
	    positionOfDot = emailAddress.lastIndexOf(".");
		lengthOfEmailAddress = emailAddress.length;

		if (positionOfAT == -1) {//����@,���Ϸ�
			valid = false;
			return;
		} else {//��@
			if (positionOfAT == 0) {//@�ǵ�һ���ַ�,���Ϸ�
				valid = false;
				return;
			} else if (positionOfAT == (lengthOfEmailAddress - 1)) {//@�����һ���ַ�,���Ϸ�
				valid = false;
				return;
			} else if (positionOfDot == -1) {
				valid = false;
				return;
			}else if (positionOfDot == (lengthOfEmailAddress - 1)) { //.����� ���Ϸ�
				valid = false;
				return;
			}else if (positionOfDot == 0) { //.����ǰ ���Ϸ�
				valid = false;
				return;
			}else if ((positionOfDot-positionOfAT) < 2 ) { //.@��һ�� ���Ϸ�
				valid = false;
				return;
			}
			
			for (var i = 0; i < emailAddress.length; i++) {
			    if (validString.indexOf(emailAddress.charAt(i)) == -1) {
			        valid = false;
					break;
					return;
				}
			}
		}
	}
	//������֤���
	return valid;
}

function checkPassword (password) {

   /**
	* ����ֵ(boolean),��������Ƿ�Ϸ�
	*/
	var valid = true;


   /**
	* �������������Ƿ�Ϸ�
	* ��֤��̰�(
	*	- ��֤�Ƿ�Ϊ��
	*	- λ���Ƿ��ǺϷ���λ��
	*/
	if (password == "") {//�ж������Ƿ�Ϊ��
		valid = false;
		return;
	} else {//���벻Ϊ��,�����������֤���
		if (password.length > maxPasswordLength || password.length < minPasswordLength) {//��֤���볤���Ƿ��ںϷ�λ��֮��
			valid = false;
			return;
		}
	}
	//������֤���
	return valid;
}

/**
 * �����ַ�
 *
 * @param str Ҫ�����ַ�
 *
 * @return �Ƿ�Ϊ��
 */
function checkNull(str) {
	var str1= trim(str,"");
	if (str1 == "") {
		return false;	
	} else {
		return true;
	}
}

function comparePassword(str1, str2) {
	if (str1 != str2) {
		return false;
		return;
	} else {
		return true;
	}
}

function checkTime(dateStr){

 	var result = false;	

	var timeStr = dateStr;	

	var reSpaceCheck = /^(\d{4})\-(\d{2})\-(\d{2})\s{1}(\d{2}):(\d{2}):(\d{2})$/;
			
	if (reSpaceCheck.test(timeStr)) {

		timeStr.match(reSpaceCheck);

		if (RegExp.$1 <= 3000 && RegExp.$1 >= 2000 && 
			RegExp.$2 <= 13 && RegExp.$2 >= 1 && 
			RegExp.$3 <= 31 && RegExp.$3 >= 1 && 
			RegExp.$4 <= 59 && RegExp.$4 >= 0 && 
			RegExp.$5 <= 59 && RegExp.$5 >= 0 && 
			RegExp.$6 <= 59 && RegExp.$6 >= 0) {

			result = true;
		}
	}	
	return result;

}

function checkPhoneNumber(num) {

	var len = num.length;

	for(var i = 0; i < len; i ++) {
		if(num.charAt(i) > '9' || num.charAt(i) < '0' || num.charAt(i)!='-') {
			return false;
			return;
		}
	}

	if((parseInt(num.substring(0,3)) < 135) || (parseInt(num.substring(0,3)) > 139)) {
		return false;
	}

	return true;
}

function checkNumber(num) {
	/**
	 * �ַ���
	 */
	var len = num.length;

	for(var i = 0; i < len; i ++) {
		if(num.charAt(i) > '9' || num.charAt(i) < '0') {
			return false;
			return;
		}
	}

	return true;
}

function getStrBytes(varStr) {
    var counter = 0;	
    for (i = 0; i< varStr.length; i++) {    	
    	if (varStr.charCodeAt(i) > 127 || varField.value.charCodeAt(i) == 94) {
    		counter+=2; 	
	} else {
	        counter++;	
	}
    }	 
    return counter; 	
 }  
   
 function getLeftChars(varField) {
    var i = 0;
    var counter = 0;
    var cap = 140;    
    
    for (i = 0; i< varField.value.length; i++) {    	
    	if (varField.value.charCodeAt(i) > 127 || varField.value.charCodeAt(i) == 94) {
    		cap = 70;    	
		} 
    }
    
    var leftchars = cap - varField.value.length;    
    
    return (leftchars);
 }
  
 function checkChars() {
 	var vLeftChars = getLeftChars(document.frm.content);
 	if (vLeftChars >= 0) {
 		return true; 			
 	} else {
       	return false;
 	}	
}
 
 function onCharsChange(varField) {
     var leftChars = getLeftChars(varField);
     if ( leftChars >= 0) {	 	
		document.all.charLeft.innerHTML = leftChars;
 		return true;
     } else {
		document.all.charLeft.innerHTML = "0";
     	window.alert("�������ݳ�����������!");
     	var len = document.frm.content.value.length + leftChars; 	
	 	document.frm.content.value = document.frm.content.value.substring(0, len);
 		leftChars = getLeftChars(document.frm.content);
     	if ( leftChars >= 0) {	 	
			document.all.charLeft.innerHTML = leftChars;
		}
        return false;    	
     }	
 }
   

function textSetFocus() {
    document.frm.content.focus();	
    return;	
}

function checkPhone(num) {
	
	var len = num.length;

	for(var i = 0; i < len; i ++) {
		if(num.charAt(i) > '9' || num.charAt(i) < '0'   ) {
			if(num.charAt(i)!='-'){
			return false;
			}
		}
	}
	return true;
}

function checkFeeRate(num) {
	/**
	 * �ַ���
	 */
	var len = num.length;

	for(var i = 0; i < len; i ++) {
		if(num.charAt(i) > '9' || num.charAt(i) < '0'   ) {
			if(num.charAt(i)!='.'){
			return false;
			}
		}
	}
	return true;
}	

/**
* 判断输入是否为整数
* 包含输入空值处理
* liukaiwei 2010-5-17
*/
function checkIsInt(num){
   var r = /^\d+$/;　//正整数+0 
   return r.test(num);  
}

/**
* 时间比较
* 格式为 YYYY-MM-DD
* 如果 a > b 返回 false
*/
function CompareDate(start_date,end_date){   
	start_date= start_date.replace(/-/g, "/"); 
	end_date = end_date.replace(/-/g, "/"); 
	start_date = new Date(start_date);
	end_date = new Date(end_date);
	if(start_date.getTime() > end_date.getTime()){
		return false;
	}
	return true;
}



