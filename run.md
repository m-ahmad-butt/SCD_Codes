# Run commands

From the repo root. PowerShell splits `-Dexec.mainClass=...` unless the whole `-D` flag is quoted.

```powershell
mvn exec:java "-Dexec.mainClass=org.example.PACKAGE.ClassName"
```

JavaFX uses a different plugin (change `<mainClass>` in `pom.xml` first if needed):

```powershell
mvn javafx:run
```

Sockets and RMI: start the **server** in one terminal, then the **client** in another.

---

## JavaFX (`javafx/`)

```powershell
mvn javafx:run
```

Default: `org.example.javafx.HelloFx`. Other classes (edit `pom.xml`):

- `org.example.javafx.HelloFx2`
- `org.example.javafx.panes.Panes`

Or:

```powershell
mvn exec:java "-Dexec.mainClass=org.example.javafx.HelloFx"
mvn exec:java "-Dexec.mainClass=org.example.javafx.HelloFx2"
mvn exec:java "-Dexec.mainClass=org.example.javafx.panes.Panes"
```

---

## Swing layouts (`layouts/`)

```powershell
mvn exec:java "-Dexec.mainClass=org.example.layouts.BorderLayoutDemo"
mvn exec:java "-Dexec.mainClass=org.example.layouts.BoxLayoutShowcase"
mvn exec:java "-Dexec.mainClass=org.example.layouts.FlowLayoutShowcase"
mvn exec:java "-Dexec.mainClass=org.example.layouts.LayoutComparisonDemo"
```

---

## Graphics (`graphics/`)

```powershell
mvn exec:java "-Dexec.mainClass=org.example.graphics.JStarDemo"
mvn exec:java "-Dexec.mainClass=org.example.graphics.Draw"
mvn exec:java "-Dexec.mainClass=org.example.graphics.fall2022.Question4"
```

---

## MVC (`mvc/`)

```powershell
mvn exec:java "-Dexec.mainClass=org.example.mvc.MVCCalculator"
mvn exec:java "-Dexec.mainClass=org.example.mvc.MVCTemperatureConverter"
mvn exec:java "-Dexec.mainClass=org.example.mvc.multimvc.MultiControllerDemo"
```

---

## Layered architecture (`layeredarchitecture/`)

```powershell
mvn exec:java "-Dexec.mainClass=org.example.layeredarchitecture.ShopGui"
```

---

## Tables (`table/`)

```powershell
mvn exec:java "-Dexec.mainClass=org.example.table.TableHierarchyDemo"
mvn exec:java "-Dexec.mainClass=org.example.table.todolist.TodoListUi"
mvn exec:java "-Dexec.mainClass=org.example.table.shoppingcart.CartController"
```

---

## JDBC (`jdbc/`)

```powershell
mvn exec:java "-Dexec.mainClass=org.example.jdbc.StudentDAO"
mvn exec:java "-Dexec.mainClass=org.example.jdbc.TodoDB"
```

---

## ORM / Hibernate (`orm/`)

```powershell
mvn exec:java "-Dexec.mainClass=org.example.orm.Main"
```

---

## Generics (`generic/`, `genericthreads/`)

```powershell
mvn exec:java "-Dexec.mainClass=org.example.generic.GenericStack"
mvn exec:java "-Dexec.mainClass=org.example.genericthreads.GenericThreadsDemo"
```

---

## Threads (`threads/`)

```powershell
mvn exec:java "-Dexec.mainClass=org.example.threads.BubbleSortExample"
mvn exec:java "-Dexec.mainClass=org.example.threads.loadingbar.LoadingBar"
mvn exec:java "-Dexec.mainClass=org.example.threads.producerconsumer.Main"
mvn exec:java "-Dexec.mainClass=org.example.threads.quiz.CountdownDemo"
```

---

## Sockets (`sockets/`)

Start server first, then client.

```powershell
mvn exec:java "-Dexec.mainClass=org.example.sockets.SimpleServer"
mvn exec:java "-Dexec.mainClass=org.example.sockets.SimpleClient"

mvn exec:java "-Dexec.mainClass=org.example.sockets.EchoServer"
mvn exec:java "-Dexec.mainClass=org.example.sockets.EchoClient"

mvn exec:java "-Dexec.mainClass=org.example.sockets.PracticeServer"
mvn exec:java "-Dexec.mainClass=org.example.sockets.PracticeClient"

mvn exec:java "-Dexec.mainClass=org.example.sockets.guessinggame.GuessingServer"
mvn exec:java "-Dexec.mainClass=org.example.sockets.guessinggame.GuessingClient"

mvn exec:java "-Dexec.mainClass=org.example.sockets.rockpaper.RockPaperServer"
mvn exec:java "-Dexec.mainClass=org.example.sockets.rockpaper.RockPaperClient"

mvn exec:java "-Dexec.mainClass=org.example.sockets.numbergame.GameServer"
mvn exec:java "-Dexec.mainClass=org.example.sockets.numbergame.GameClient"

mvn exec:java "-Dexec.mainClass=org.example.sockets.measureo2.O2Server"
mvn exec:java "-Dexec.mainClass=org.example.sockets.measureo2.O2Client"

mvn exec:java "-Dexec.mainClass=org.example.sockets.attendance.AttendanceServer"
mvn exec:java "-Dexec.mainClass=org.example.sockets.attendance.AttendanceClient"

mvn exec:java "-Dexec.mainClass=org.example.sockets.bulb.BulbServer"
mvn exec:java "-Dexec.mainClass=org.example.sockets.bulb.BulbClient"
```

---

## RMI (`rmi/`)

Start server first, then client.

```powershell
mvn exec:java "-Dexec.mainClass=org.example.rmi.ServerMain"
mvn exec:java "-Dexec.mainClass=org.example.rmi.ClientMain"

mvn exec:java "-Dexec.mainClass=org.example.rmi.studenteligibility.ServerMain"
mvn exec:java "-Dexec.mainClass=org.example.rmi.studenteligibility.Client"
```

---

## Web (`web/`)

```powershell
mvn exec:java "-Dexec.mainClass=org.example.web.GetReq"
mvn exec:java "-Dexec.mainClass=org.example.web.PostSwing"
```

---

## File handling (`filehandling/`)

```powershell
mvn exec:java "-Dexec.mainClass=org.example.filehandling.FileHandlerExample"
```

---

## Serialization (`serialization/`)

```powershell
mvn exec:java "-Dexec.mainClass=org.example.serialization.Student"
```

---

## Reflection (`reflection/`)

```powershell
mvn exec:java "-Dexec.mainClass=org.example.reflection.ReflectionDemo"
mvn exec:java "-Dexec.mainClass=org.example.reflection.ReflectionShapesDemo"
mvn exec:java "-Dexec.mainClass=org.example.reflection.ReflectionPractice"
```

---

## Annotations (`annotation/`)

```powershell
mvn exec:java "-Dexec.mainClass=org.example.annotation.AnnotationDemo"
```

---

## Exam practice (`exampractice/`)

```powershell
mvn exec:java "-Dexec.mainClass=org.example.exampractice.ListenerDemo"
mvn exec:java "-Dexec.mainClass=org.example.exampractice.MidLayoutDemo"
mvn exec:java "-Dexec.mainClass=org.example.exampractice.MVC"
mvn exec:java "-Dexec.mainClass=org.example.exampractice.SplitLayoutDemo"
mvn exec:java "-Dexec.mainClass=org.example.exampractice.TableDemo"
mvn exec:java "-Dexec.mainClass=org.example.exampractice.TwoRowsLayout"
mvn exec:java "-Dexec.mainClass=org.example.exampractice.mid1.MidGui"
mvn exec:java "-Dexec.mainClass=org.example.exampractice.mid1.MidGui2"
```

Quiz answers:

```powershell
mvn exec:java "-Dexec.mainClass=org.example.exampractice.quiz.StudentDal"
mvn exec:java "-Dexec.mainClass=org.example.exampractice.quiz.Vector"
mvn exec:java "-Dexec.mainClass=org.example.exampractice.quiz.FileDownloadUi"
```

---

## Tests

```powershell
mvn test
```

---

## Ant (quiz jars)

See [ant/ant.md](ant/ant.md).
