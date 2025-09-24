package model

import metaModel.IFieldType

/**
 * [isMarked] (isImportant)
 * [txMode] /transaction mode/ { +..., UPI, CASH, CREDIT_CARD, DEBIT_CARD}
 * @sample IEntity(name = "UPI", notes = "PhonePay", image = /UPI Icon/)
 * [repetitions] //Use for recursive Transactions & Set one [reminders] for each repetition//
 * [categories] { +..., FOOD, LOAN_REPAYMENT, LOAN, INVESTMENT, RENT, FAMILY, SALARY, TAX, OTHER, DONATION}
 * */
interface ITransaction: IEvent {
    val amount: Float
    val payer: IPerson
    val txType: TxType?
    val txMode: IEntity?
    val receiver: IPerson?
    val txStatus: TxStatus?
}

data class Transaction(
    override val amount: Float,
    override val payer: IPerson,
    override val txType: TxType? = null,
    override val txMode: IEntity? = null,
    override val receiver: IPerson? = null,
    override val txStatus: TxStatus? = null,
    private val iEvent: IEvent
): ITransaction, IEvent by iEvent

sealed interface TxType: IFieldType {
    data object Paid: TxType
    data object Took: TxType
    data object Debt: TxType
    data object Credit: TxType
}

sealed interface TxStatus: IFieldType {
    data object Asset: TxStatus
    data object Liability: TxStatus
    data object Inflow: TxStatus
    data object Outflow: TxStatus
    data object Income: TxStatus
    data object Expense: TxStatus
}