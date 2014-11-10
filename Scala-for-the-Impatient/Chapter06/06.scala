object PockerSuits extends Enumeration{
	type PockerSuits = Value
	var HEART = Value("♠")
	var SPADE = Value("♣")
	var CLUB = Value("♥")
	var DIAMOND = Value("♦")
}

def isRed(suit : PockerSuits.Value) = (suit == PockerSuits.HEART || suit == PockerSuits.DIAMOND)
println(for(s <- PockerSuits.values) yield(s, isRed(s)))
