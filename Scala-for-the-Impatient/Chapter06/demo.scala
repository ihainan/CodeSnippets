class Accounts(val id:Int, initialBalance:Double){
	private var account:Double = initialBalance
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

object Accounts{
	def apply(initialBalance:Double) = new Accounts(newUniqueNumber(), initialBalance)
	private var lastNumber = 0
	def newUniqueNumber() = {lastNumber += 1; lastNumber}
}

abstract class UndoableAction(val description : String){
	def undo():Unit
	def redo():Unit
}

object DoNothingAction extends UndoableAction("Do nothing"){
	override def undo() {}
	override def redo() {}
}

val a = Accounts(1000.0)
val b = Accounts(1000.0)
println(a.id + " : " + a.balance)
println(b.id + " : " + b.balance)

