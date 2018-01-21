function logIn()
{
    //document.getElementById("logForm").style.display='block';
}

function regIn()
{alert("odpalam");
    //document.getElementById("regForm").style.display='block';
    
}

function check() 
 { alert("odpalam");
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
