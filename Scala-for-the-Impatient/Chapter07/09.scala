import java.lang.System._
var userName = getProperty("user.name")
var passWord = readLine("Input your password : ")
if(passWord == "secret")
	println("Your user name : " + userName)
else
	println("Wrong password!")
