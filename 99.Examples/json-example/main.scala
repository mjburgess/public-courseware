import org.json4s._
import org.json4s.native.JsonMethods._

case class EmailAccount (
    accountName: String, 
    url: String, 
    username: String,
    usersOfInterest: List[String]
)

object JsonEg extends App {
    implicit val df = DefaultFormats

    val json = parse("""{
    "accounts": [ { "email": {
        "accountName": "YMail",
        "username": "USERNAME",
        "url": "imap.yahoo.com",
        "usersOfInterest": ["barney", "betty"]
    }}, { "email": {
        "accountName": "Gmail",
        "username": "USER",
        "url": "imap.gmail.com",
        "usersOfInterest": ["pebbles", "bam-bam"]
    }}
    ]}""")


    var els = (json \\ "email").children map {
        _.extract[EmailAccount]
    }

    println(els)
}

