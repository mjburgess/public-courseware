
case class Amount(val name: String, val lower: Int = 1, val upper: Int = 1)
{
  override def toString: String = s"$lower" + (if(upper > lower) s"to $upper" else "") + "x" + name;
}

case class Ingredient(val name: String, val features: Seq[String] = Nil)
{
  override def toString: String = name + (if(features.isEmpty) "" else features.mkString(", "))
}

case class Dish(val name: String,
                val prepTime: Int,
                val cookTime: Int,
                val ingredients: Map[Ingredient, Amount],
                val prepNotes: Seq[String] = Nil,
                val cookNotes: Seq[String] = Nil)
{
  override def toString: String =
      s"$name ( P: $prepTime m,  C: $cookTime m )\n" +
      prepNotes.mkString("\n") + "\n"
      cookNotes.mkString("\n")
}

case class Meal(val name: String,
                val dishes: Seq[Dish],
                val notes: String = "")
{
  override def toString: String = name + ":\n" + dishes.map(_.toString).mkString("\n")
}

val rice = Ingredient("Basmati Rice")
val tumeric = Ingredient("Tumeric")
val cumin = Ingredient("Cumin")
val salt = Ingredient("Salt")
val water = Ingredient("Water")

val dusting = Amount("dusting")
val handful1to2 = Amount("handful", 1, 2)
val dblRice = Amount("volume of rice", 2)

val yRice = Dish("Yellow Rice", 5, 15,
  Map(
    rice -> handful1to2,
    tumeric -> dusting,
    cumin -> dusting,
    salt -> dusting,
    water -> dblRice
  ),
  Seq("Boil then Simmer rice", "Season everything"),
  Seq("Stop rice just before soft")
)

val chRiceMeal = Meal("Chicken & Rice", Seq(yRice))

println(chRiceMeal)

/*      MEAL PAGE:

MEAL:       Chicken & Rice                  PREP:  10 - 15m
                                            COOK: 15m - 30m
  * Chicken Breast
  * Cumin Rice


INGREDIENTS:
  + Chicken Breast  -   1
  + Rice            -   1 to 2x handful
  + Water           -   2x volume of rice
  + Cumin           -   dusting
  + Tumeric         -   dusting
  + Salt            -   dusting



PREP:
  - Boil then simmer rice.
  - Season everything.

COOKING:
  - Cook breast 2/3rds one side on medium heat, then turn.
  - Rice just before soft.

NOTES:
  * Cook rice after breast, leave chicken to rest.

*/

/*    DISH PAGE:

DISH:       Yellow Rice                    PREP:  5m
                                          COOK: 15m

INGREDIENTS:
  + Rice          -   1 to 2x  handful
  + Water         -   2x volume of rice
  + Cumin         -   dusting
  + Tumeric       -   dusting
  + Salt          -   dusting


PREP:
  - Boil then simmer rice.
  - Season everything.


COOKING:
  - Rice just before soft.

 */
