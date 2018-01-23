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
   alert("Please Enter Your First Name!");
   return false ;
  } 
  if (document.forms[0].lastname.value == "") { 
   alert("Please Enter your Last Name!") ;
   return false; 
  } else { 
   return true; 
  } 
 } 
 /*
function changeLogInButton()
{
    if()
    document.getElementById("requestLogIn").value="Wyloguj się";
}*/

function showHideSearchForms(which)
{
    if(which==1){
        document.getElementById("searchProductByName").style.display="block";
        document.getElementById("searchMealByName").style.display="none";
        document.getElementById("searchMealByProduct").style.display="none";
    }
    if(which==2){
        document.getElementById("searchProductByName").style.display="none";
        document.getElementById("searchMealByName").style.display="block";
        document.getElementById("searchMealByProduct").style.display="none";    
    }
    if(which==3){
        document.getElementById("searchProductByName").style.display="none";
        document.getElementById("searchMealByName").style.display="none";
        document.getElementById("searchMealByProduct").style.display="block";    
    }
}
function showHideaddProductByNameForm()
{
    if(document.getElementById("showDiv").style.display=="none")
        document.getElementById("showDiv").style.display="block";
    else
    {
        document.getElementById("showDiv").style.display="none";
    }
}

function showHidesetYourGoals()
{
    if(document.getElementById("showDiv2").style.display=="none")
        document.getElementById("showDiv2").style.display="block";
    else
    {
        document.getElementById("showDiv2").style.display="none";
    }
}
function showHideaddYourWeightForm()
{
    if(document.getElementById("showDiv3").style.display=="none")
        document.getElementById("showDiv3").style.display="block";
    else
    {
        document.getElementById("showDiv3").style.display="none";
    }
}

function showHideaddMyProductForm()
{
    if(document.getElementById("showDiv4").style.display=="none")
        document.getElementById("showDiv4").style.display="block";
    else
    {
        document.getElementById("showDiv4").style.display="none";
    }
}

function showHideDiv10()
{
    if(document.getElementById("showDiv10").style.display=="none")
        document.getElementById("showDiv10").style.display="block";
    else
    {
        document.getElementById("showDiv10").style.display="none";
    }
}

function showHideDiv11()
{
    if(document.getElementById("showDiv11").style.display=="none")
        document.getElementById("showDiv11").style.display="block";
    else
    {
        document.getElementById("showDiv11").style.display="none";
    }
}

function showHideDiv12()
{
    if(document.getElementById("showDiv12").style.display=="none")
        document.getElementById("showDiv12").style.display="block";
    else
    {
        document.getElementById("showDiv12").style.display="none";
    }
}
function showHideDiv13()
{
    if(document.getElementById("showDiv13").style.display=="none")
        document.getElementById("showDiv13").style.display="block";
    else
    {
        document.getElementById("showDiv13").style.display="none";
    }
}
function showHideDiv14()
{
    if(document.getElementById("showDiv14").style.display=="block")
        document.getElementById("showDiv14").style.display="none";
    else
    {
        document.getElementById("showDiv14").style.display="block";
    }
}

function showHideDiv6()
{
    if(document.getElementById("showDiv6").style.display=="block")
        document.getElementById("showDiv6").style.display="none";
    else
    {
        document.getElementById("showDiv6").style.display="block";
    }
}
function showHideDiv7()
{
    var currentTime = new Date();
    
    if(document.getElementById("showDiv7").style.display=="block")
        document.getElementById("showDiv7").style.display="none";
    else
    {
        document.getElementById("showDiv7").style.display="block";
    }

}
function showHideDiv8()
{
    if(document.getElementById("showDiv8").style.display=="block")
        document.getElementById("showDiv8").style.display="none";
    else
    {
        document.getElementById("showDiv8").style.display="block";
    }
}
function hideNavDivs()
{
    var now = new Date();
    var day = ("0" + now.getDate()).slice(-2);
    var month = ("0" + (now.getMonth() + 1)).slice(-2);
    var today = now.getFullYear()+"-"+(month)+"-"+(day) ;

var list = document.getElementsByClassName('actualDate');
var n;
for (n = 0; n < list.length; ++n) {
    list[n].value=today;
}

    var monthago = now.getFullYear()+"-"+(month)+"-"+(day) ;
var list = document.getElementsByClassName('monthAgo');
var n;
for (n = 0; n < list.length; ++n) {
    list[n].value='2018-01-01';
}
    document.getElementById("Products").style.display="none";
    document.getElementById("Meals").style.display="none";
    document.getElementById("Favourites").style.display="none";
    document.getElementById("My").style.display="none";
    document.getElementById("History").style.display="none";
    
    
}
function Products()
{
    hideNavDivs();
    document.getElementById("Products").style.display="block";

}
function Meals()
{
    hideNavDivs();
    document.getElementById("Meals").style.display="block";

}
function Favourites()
{
    hideNavDivs();
    document.getElementById("Favourites").style.display="block";

}
function My()
{
    hideNavDivs();
    document.getElementById("My").style.display="block";

}
function History()
{
    hideNavDivs();
    document.getElementById("History").style.display="block";

}

function insertDate()
{



}