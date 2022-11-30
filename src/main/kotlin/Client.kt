import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.html.*
import kotlinx.html.dom.append
import kotlinx.html.js.onChangeFunction
import org.w3c.dom.*
import org.w3c.dom.events.Event

fun main() {
  window.onload = { document.body?.sayHello() }
}

fun Node.sayHello() {
  append {
    val students:Array<String> = arrayOf("Adil", "Sam", "Ken", "Ed", "Bran")
    div {
      table {
        thead {
          tr {
            td { +"Student" }
            td { +"Rating" }
          }
        }
        tbody {
          for (i in 0 .. 4){
            tr{
              td { +"${students[i]}" }
              td { +"-"
                  id = "oneStudentRating$i"
              }
              td {
                  for (k in 1 .. 5){
                    input {
                      onChangeFunction = ::onChange
                      type = InputType.radio
                      name = "student$i"
                      id = "rating$k"
                      value = "$k"
                    }
                    label {
                      +"$k"
                      id = "input$k"
                    }
                  }
              }
            }
          }
          tr {
            td {
              +"Average rating:"
            }
            td {
              label{
                id = "calc1"
                +"0"
              }
            }
          }
        }
      }
    }
  }
  }

fun onChange(e: Event){
  var calc = document.getElementById("calc1")
  var default = 0.0
  for (i in 0..4) {
    val temp = document.querySelector("input[name='student${i}']:checked")
    var oneStudentRating = document.getElementById("oneStudentRating$i")
    if(temp != null) {
      if (oneStudentRating != null) {
        oneStudentRating.textContent = ((temp as HTMLInputElement).value.toDouble()).toString()
      }
      default += (temp as HTMLInputElement).value.toDouble()
    }
  }


  if (calc != null) {
    calc.textContent = (((default) / 5).toString())
  }
}

