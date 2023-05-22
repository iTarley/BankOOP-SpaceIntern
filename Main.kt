open class Person(
        val fullName: String,
        var income: Float,
        var balance: Float
)

class Citizen(
        fullName: String,
        income: Float,
        balance: Float,
        val citizenship: String
) : Person(fullName, income, balance)

class Entrepreneur(
        fullName: String,
        income: Float,
        balance: Float,
        val fieldOfProduction: String
) : Person(fullName, income, balance)

class Politician(
        fullName: String,
        income: Float,
        balance: Float,
        val party: String
) : Person(fullName, income, balance)

class Bank {
    private var commissionCollected: Float = 0.0f

    fun transferMoney(sender: Person, receiver: Person, amount: Float) {
        when (sender) {
            is Citizen -> {
                sender.balance -= amount
                receiver.balance += amount
            }

            is Entrepreneur -> {
                val transferAmount = amount * 0.9f
                sender.balance -= amount
                receiver.balance += transferAmount
                commissionCollected += amount - transferAmount
            }

            is Politician -> {
                val transferAmount = amount * 0.8f
                sender.balance -= amount
                receiver.balance += transferAmount
                commissionCollected += amount - transferAmount
            }
        }
    }

    fun deductMonthlyFee(user: Person) {
        val feePercentage = when (user) {
            is Citizen -> 0.02f
            is Entrepreneur -> 0.04f
            is Politician -> 0.06f
            else -> 0.0f
        }
        val fee = user.balance * feePercentage
        user.balance -= fee
        commissionCollected += fee
    }

    fun getCommissionCollected(): Float {
        return commissionCollected
    }
}

fun main() {
    val luka = Citizen("ლუკა ნიკურაძე", 600.0f, 1500.0f, "საქართველო")
    val walter = Entrepreneur("ვოლტერ ვაით", 42069.0f, 9999999.0f, "The cook")
    val trump = Politician("დონალდ ტრამპი", 2024069.0f, 53503012.0f, "Republican Party")

    val bank = Bank()
    bank.transferMoney(luka, walter, 500.0f)
    bank.transferMoney(trump, luka, 2000.0f)
    bank.transferMoney(walter, trump, 40.0f)

    bank.deductMonthlyFee(luka)
    bank.deductMonthlyFee(walter)
    bank.deductMonthlyFee(trump)

    println("Commission collected by the bank: ${bank.getCommissionCollected()}")
    println("Luka's balance: ${luka.balance}")
    println("Walter's balance: ${walter.balance}")
    println("Trump's balance: ${trump.balance}")
}
