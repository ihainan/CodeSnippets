class BankAccount{
	private var account:Double = 0.0
	def balance = account
	def desposit(value : Double){
		account += value
	}

	def withdraw(value : Double){
		if(account >= value){
			account = account - value
		}
		else{
			throw new Exception("Your balance " + balance + " is less than " + value)
		}
	}
}

val ac = new BankAccount
ac.desposit(100)
try{
	ac.withdraw(110)
}
catch{
	case e : Exception => println(e.getMessage)
}
println(ac.balance)
