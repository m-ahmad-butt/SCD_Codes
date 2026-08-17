This repo is a set of Software Construction and Development (SCD) demos. Each topic lives in its own package under `src/main/java/org/example/`.

## Layout

```
src/main/java/org/example/
  annotation/              custom annotations
  exampractice/            extra GUI / mid practice (not the main topic demos)
  filehandling/            reading and writing text files
  generic/                 generic stack
  genericthreads/          generics + threads
  graphics/                Swing custom painting (JStar, past paper Q4)
  javafx/                  JavaFX + FXML
  jdbc/                    JDBC + SQLite
  layeredarchitecture/     GUI → service → DAO + model
  layouts/                 Swing layout managers
  mvc/                     MVC calculator / temperature converter
  orm/                     Hibernate
  reflection/              reflection over a shape hierarchy
  rmi/                     RMI client/server
  serialization/           object serialization
  sockets/                 TCP client/server demos and games
  table/                   JTable + shopping cart + todo list
  testing/                 classes under test (JUnit lives in src/test/java)
  threads/                 threading, producer-consumer, loading bar
  web/                     HTTP GET/POST with Swing

docs/                      cheat sheets (extends vs implements, JAR commands)
ant/build.xml              Ant compile / jar / sign example
Notes/                     course notes
Slides/                    lecture slides
data/                      SQLite DBs and generated files (created at runtime)
```

## Prerequisites

- JDK 21
- Maven 3.x
- IntelliJ IDEA (or any Maven-aware IDE)

## How to run

Full command list for every demo: [run.md](run.md).

### JavaFX

Default main class is `org.example.javafx.HelloFx`.

```bash
mvn javafx:run
```

To run a different JavaFX class, change `<mainClass>` in `pom.xml`:

- `org.example.javafx.HelloFx`
- `org.example.javafx.HelloFx2`
- `org.example.javafx.panes.Panes`

### Swing and other `main` classes

PowerShell splits `-Dexec.mainClass=...`, so quote the whole `-D` flag:

```powershell
mvn exec:java "-Dexec.mainClass=org.example.mvc.MVCCalculator"
```

Examples:

| Demo | Main class |
| --- | --- |
| Graphics | `org.example.graphics.JStarDemo` |
| BorderLayout | `org.example.layouts.BorderLayoutDemo` |
| BoxLayout | `org.example.layouts.BoxLayoutShowcase` |
| FlowLayout | `org.example.layouts.FlowLayoutShowcase` |
| Layout comparison | `org.example.layouts.LayoutComparisonDemo` |
| MVC calculator | `org.example.mvc.MVCCalculator` |
| Layered architecture | `org.example.layeredarchitecture.ShopGui` |
| Mid GUI practice | `org.example.exampractice.mid1.MidGui` |
| Table demo | `org.example.exampractice.TableDemo` |

You can also open any class with a `main` method and run it from the IDE.

### Tests

```bash
mvn test
```

JUnit tests are in `src/test/java/org/example/testing/`.

## Database

SQLite files are written under `data/` (see `src/main/resources/system.properties`). Hibernate demos use an in-memory H2 database configured in `src/main/resources/hibernate.cfg.xml`.

## Past papers

[SCD past papers](https://github.com/saleha-muzammil/Academic-Time-Machine/tree/main/SCD)
