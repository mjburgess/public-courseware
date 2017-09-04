package inheritance

// Two sub-packages, inheritance.one and inheritance.two, are defined in this file
package one {
	class Item(name: String, cost: Double) {
		val _name = name
		val _cost = cost
		
		def discountedCost(discount: Double): Double = _cost - (_cost * discount/100.0)
	}
}

package two {
	package object Discount {
		val low = 10.0                    //> low  : Double = 10.0
		val medium = 15.0                 //> medium  : Double = 15.0
		val high = 20.0                   //> high  : Double = 20.0
	}
}