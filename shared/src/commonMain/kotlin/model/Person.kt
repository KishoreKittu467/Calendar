package model

import metaModel.FieldType

/**
 * [id] == null ? /System or Main User/ : /Other User/
 * [phone]
 * @sample IEntity(name = "9876543210", notes = "+91", image = Image(icon = "🇮🇳"))
 * [socialLinks] {Fb, Instagram, WhatsApp, StackOverflow, Github, Reddit, Snapchat}
 * @sample IEntity(name = "Github", notes = "www.github.com/userId", image = Image(icon = "🐈‍⬛"))
 * [borrowedAmount] //Main User =Gave=> IPerson//amount borrowed by IPerson from the Main User//
 * [lentAmount] //IPerson =Gave=> Main User//amount lent by IPerson to the Main User//
 * [inFlow] //Main User =Gave=> IPerson//amount gave away by Main user to IPerson//
 * [outFlow] //IPerson =Gave=> Main User//amount gave away by IPerson to the Main User//
 * directBalance = [borrowedAmount] - [lentAmount] //Pending amount IPerson owe to Main User //
 * inDirectBalance = [outFlow] - [inFlow] //Amount freely spent by Main User to IPerson //
 * [relation] { +..., Sis, Bro, Male Friend, Female Friend, Male Cousin, Female Cousin, Other}, Mom, Dad, Uncle, Aunt, Other, Colleague}
 * @sample IEntity(name = "Mom", notes = "", image = Image(icon = "🤱🏻"))
 * [contactGroup] { +..., Family, Friend, Colleague, Bank, Customer Care, Other}
 * @sample IEntity(name = "Family", notes = "", image = Image(icon = "👨‍👩‍👧‍👦"))
 * */
interface IPerson: IEntity {
    val id: String?
    val lastName: String?
    val middleName: String?
    val phone: IEntity?
    val email: String?
    val phoneNumbers: LinkedHashSet<IEntity>?
    val emails: LinkedHashSet<String>?
    val dob: Long?
    val gender: Gender?
    val socialLinks: LinkedHashSet<IEntity>?
    val relation: IEntity?
    val contactGroup: IEntity?
    val gallery: LinkedHashSet<IBookMark>?
    val borrowedAmount: Float?
    val lentAmount: Float?
    val inFlow: Float?
    val outFlow: Float?
}

data class Person(
    override val id: String? = null,
    override val lastName: String? = null,
    override val middleName: String? = null,
    override val phone: IEntity? = null,
    override val email: String? = null,
    override val phoneNumbers: LinkedHashSet<IEntity>? = null,
    override val emails: LinkedHashSet<String>? = null,
    override val dob: Long? = null,
    override val gender: Gender? = null,
    override val socialLinks: LinkedHashSet<IEntity>? = null,
    override val relation: IEntity? = null,
    override val contactGroup: IEntity? = null,
    override val gallery: LinkedHashSet<IBookMark>? = null,
    override val borrowedAmount: Float? = null,
    override val lentAmount: Float? = null,
    override val inFlow: Float? = null,
    override val outFlow: Float? = null,
    private val iEntity: IEntity
): IPerson, IEntity by iEntity

sealed interface Gender: FieldType {
    data object Male: Gender
    data object Female: Gender
    data object Other: Gender
}