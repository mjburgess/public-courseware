//MORE SCALAZ

//https://alvinalexander.com/scala/scala-jdbc-connection-mysql-sql-select-example
//https://alvinalexander.com/scala/scala-rest-client-apache-httpclient-restful-clients
//https://alvinalexander.com/scala/scala-twitter-client-twitter-api-example

{
  //VALIDATION
}

// SOURCE
import scala.io.Source

// reading urls
println(Source.fromURL("http://google.com"))


//reading files
val filename = "/etc/services"

for (line <- Source.fromFile(filename).getLines) {
  println(line)
}


val bufferedSource = Source.fromFile(filename)

for (line <- bufferedSource.getLines) {
  println(line.toUpperCase)
}

bufferedSource.close


// java for writing files... ugly!
import java.io._
var in: Option[FileInputStream]   = None
var out: Option[FileOutputStream] = None

try {
  in  = Some(new FileInputStream(filename))
  out = Some(new FileOutputStream("/tmp/services-copy"))

  out.write(in.read(in.length()))

} catch {
  case e: IOException => e.printStackTrace
} finally {
  println("entered finally ...")

  if (in.isDefined)  in.get.close
  if (out.isDefined) out.get.close
}



// JSON

// a case class to match the JSON data

val JSON_STRING = """{
    "accounts": [
        { "emailAccount": {
        "accountName": "YMail",
        "username": "USERNAME",
        "password": "PASSWORD",
        "url": "imap.yahoo.com",
        "usersOfInterest": ["barney", "betty", "wilma"]
        } },

        { "emailAccount": {
        "accountName": "Gmail",
        "username": "USER",
        "password": "PASS",
        "url": "imap.gmail.com",
        "usersOfInterest": ["pebbles", "bam-bam"]
        } }
    ]
}
                  """

case class EmailAccount(
                         accountName: String,
                         url: String,
                         username: String,
                         password: String,
                         usersOfInterest: List[String]
                       )

implicit val formats = DefaultFormats
val json = parse(JSON_STRING)
val elements = (json \\ "emailAccount").children map { _.extract[EmailAccount] }

println(elements)


// EXERCISE

// SOURCE + JSON
//Q. use the Source scala library to read http://jsonplaceholder.typicode.com/users
//Q. use the json4s (ie., lift-web) json library to parse this into case classes

