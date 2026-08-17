# Ant — quiz JARs

From the repo root.

## 1. Build

```powershell
ant -f ant/build.xml
```

This compiles the quiz sources and writes three jars:

- `dist/quiz1.jar` — StudentDal (JDBC, filter by batch)
- `dist/quiz2.jar` — Vector (generic, reverse iterator)
- `dist/quiz3.jar` — FileDownloadUi (threads + Swing)

## 2. Check bytecode

If compile worked, you should see `.class` files here:

```
classes/org/example/exampractice/quiz/
```

Expected:

- `StudentDal.class`
- `Vector.class`
- `Vector$ReverseIterator.class`
- `FileDownloadUi.class`
- `FileDownloadUi$DownloadTask.class`

`.class` = bytecode. That means `javac` succeeded and the jars were built from these files.

## 3. Run the jars

```powershell
java -jar dist/quiz1.jar
java -jar dist/quiz2.jar
java -jar dist/quiz3.jar
```

- quiz1 prints students for batch `2022` (empty if the table has no rows)
- quiz2 prints `Reverse: 30 20 10`
- quiz3 opens the download window

Optional (sign / verify, needs a keystore):

```powershell
ant -f ant/build.xml genkey
ant -f ant/build.xml sign
ant -f ant/build.xml verify
```
