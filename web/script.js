function logIn()
{
    document.getElementById("logDiv").style.display='block';
    document.getElementById("regDiv").style.display='none';
}

function regIn()
{
    document.getElementById("logDiv").style.display='none';
    document.getElementById("regDiv").style.display='block';
}

function check() 
 {
  if (document.forms[0].firstname.value == "") { 
   alert("Please Enter Your First Name!")
   return false 
  } 
  if (document.forms[0].lastname.value == "") { 
   alert("Please Enter your Last Name!") 
   return false 
  } else { 
   return true 
  } 
 } 
