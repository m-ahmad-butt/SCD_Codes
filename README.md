This repo is for my personal practice for Software Construction and Development course, containing practical implementations and demonstrations of various advanced Java topics.

## Topics Covered
### 1. **JavaFX** (`javaFx/`)
- JavaFX fundamentals (HelloFx, HelloFx2)
- Panes and layout management
- FXML integration

### 2. **Java Swing** (`_graphics/`, `layouts/`, `practice/`)
- Custom graphics (JStar, JStarDemo, drawImage)
- Layout managers:
  - BorderLayout
  - BoxLayout
  - FlowLayout
- GUI components and event listeners
- Table components

### 3. **Database Connectivity**
- **JDBC** (`jdbc/`)
- **ORM/Hibernate** (`ORM/`)

### 4. **Architectures**
- **MVC Pattern** (`mvcArchitecture/`, `practice/`):
  - Calculator implementation
  - Temperature converter
  - Multi-MVC examples
- **Layered Architecture** (`layerdArchitecture/`): GUI, Service, DAO layers

### 5. **Network Programming**
- **RMI (Remote Method Invocation)** (`RMI/`): Client-server communication using RMI
- **Sockets** (`sockets/`): Echo client-server, simple client-server implementations

### 6. **Serialization** (`serielization/`)
- Object serialization with Student class

### 7. **Reflection** (`reflection/`)
- Shape hierarchy with reflection (Circle, Rectangle, Shape)

### 8. **Annotations** (`annotation/`)
- Custom annotation creation
- Annotation processing
- Header annotation demo

### 9. **Multithreading** (`Thread/`)
- Thread management and synchronization

### 10. **Unit Testing** (`testing/`)
- JUnit test cases

### 11. **Past Paper Solutions (Fall 2024)**
- **Smart Bulb System** (`sockets/bulb`): Smart home bulb control simulation using sockets.
- **Shopping Cart** (`table/shopping_cart`): MVC-based shopping cart implementation using 2 Swing tables.
- **Etch-a-Sketch** (`_graphics/Fall_2022/Question4.java`): Question 4 solution using Java Swing (JFrame/paintComponent) to move an 'X' with history trail.

### 12. **Additional Network Programming**
- **Number Guessing Game** (`sockets/numGuessingGame_v2`, `sockets/numberGame`): Multiplayer variants.
- **Rock Paper Scissors** (`sockets/rockPaper`): Client-Server implementation.
- **O2 Measurement System** (`sockets/measureO2`): Oxygen level monitoring simulation.
- **Attendance System** (`sockets/attendance`): Client-Server attendance tracking.

### 13. **Web Connectivity** (`Web_Connectivity/`)
- HTTP GET and POST request handling with Swing integration.

### 14. **Multithreading Patterns** (`Thread/producerConsumer`)
- **Producer-Consumer Pattern**: Implementation using `BoundedBuffer`.

### 15. **References & Cheat Sheets**
- **Notes** (`Notes/`): 
  - Archiving and Distribution
  - Github Guide
  - ORM, Beans, and Serialization
  - Object Serialization
- **Cheat Sheets**:
  - `jar.txt`: Quick reference for JAR creation and signing commands.
  - `extends_implements.txt`: Mnemonics and quick reference for `extends` vs `implements`.

## How to Run
### Prerequisites
- JDK 24 (or compatible version)
- Maven 3.x
- IDE (IntelliJ IDEA recommended)

### Running JavaFX Applications
#### Using Maven
1. **Update the main class in `pom.xml`**:
   Find the `javafx-maven-plugin` configuration section in `pom.xml` and change the `<mainClass>` to your desired JavaFX class:
   ```xml
   <configuration>
       <mainClass>org.example.javaFx.HelloFx</mainClass>
   </configuration>
   ```
   Examples:
   - For `HelloFx`: `org.example.javaFx.HelloFx`
   - For `HelloFx2`: `org.example.javaFx.HelloFx2`
   - For `Panes`: `org.example.javaFx.panes.Panes`

2. **Run the application**:
   ```bash
   mvn clean javafx:run
   ```

### Running Java Swing Applications
Java Swing applications can be run directly without additional configuration since Swing is included in the JDK.
#### Option 1: Using Maven
```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="org.example.layouts.FlowLayoutShowcase"
```
Replace the main class with any of the following:
- `org.example._graphics.JStarDemo` - Graphics demo
- `org.example.layouts.BorderLayoutDemo` - BorderLayout example
- `org.example.layouts.BoxLayoutShowcase` - BoxLayout example
- `org.example.layouts.FlowLayoutShowcase` - FlowLayout example
- `org.example.layouts.LayoutComparisonDemo` - Compare different layouts
- `org.example.practice.table` - Table component demo
- `org.example.mid1.midGUI` - GUI practice
- `org.example.mvcExample.MVCCalculator` - MVC Calculator

#### Option 2: Running from IDE
Simply run any Swing class with a `main` method directly from your IDE.

## Database Configuration
SQL-lite3
### System Properties
The `system.properties` file is already present in `src/main/resources/` (don't worry, I have pushed that as well).