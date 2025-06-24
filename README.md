# Java Networking Lab

Este proyecto es una colección de ejercicios prácticos diseñados para comprender los fundamentos de redes, esquemas de nombres, sockets, datagramas, RMI y clientes/servidores en Java. Está basado en el taller propuesto por la Escuela Colombiana de Ingeniería Julio Garavito.

---

## 📚 Contenidos del Proyecto

### 1. 🌐 Conceptos de Redes

Se exploran los protocolos TCP y UDP, así como la utilización de puertos para separar servicios. Se emplean las clases `Socket`, `ServerSocket`, `DatagramSocket`, `DatagramPacket` y `URL` de `java.net`.

---

### 2. 📡 Aplicaciones TCP

#### 🧪 Echo Server / Client
Servidor que recibe un mensaje del cliente y responde el mismo mensaje con un prefijo.

- **Tecnologías**: `Socket`, `ServerSocket`
- **Ejercicio**: Leer y enviar mensajes desde el cliente hacia el servidor.
- **Mejora**: Se desarrolló una versión multicliente con threads (`ChatServer`, `ChatClient`).

---

### 3. 🌐 Navegador Simple con URL

#### 🧪 URL Reader
- Lee datos desde una URL y los muestra por consola.
- Imprime detalles como protocolo, host, puerto, path, etc.

#### 🧪 URL Browser con Guardado
- Solicita una URL al usuario y guarda la respuesta como `resultado.html`.

---

### 4. 🖧 Sockets

#### 🧪 Servidor cuadrático
- Recibe un número y devuelve su cuadrado.

#### 🧪 Servidor funcional (sin, cos, tan)
- Procesa expresiones tipo `fun:sin`, `fun:cos`, `fun:tan` y aplica la operación actual a los siguientes números.

#### 🧪 Chat TCP con Threads
- Varios clientes se conectan a un solo servidor y pueden enviarse mensajes entre sí (broadcast).
- **Clases**: `ChatServer.java`, `ChatClient.java`

---

### 5. 🌐 Servidor Web

#### 🧪 Web Server Básico
- Atiende solicitudes HTTP simples y responde con HTML.

#### 🧪 Web Server que sirve archivos
- Atiende múltiples solicitudes secuenciales (no concurrentes).
- Capaz de servir archivos `.html`, `.jpg`, etc.

---

### 6. 📦 Datagramas (UDP)

#### 🧪 Servidor de Hora
- Servidor responde con la hora actual cuando recibe una solicitud UDP.

#### 🧪 Cliente de Hora
- Se conecta al servidor de hora cada 5 segundos y actualiza su pantalla.
- Si pierde la conexión, mantiene la hora anterior.

---

### 7. 🤝 RMI (Remote Method Invocation)

#### 🧪 Echo Server RMI
- Un servidor que expone un método remoto `echo(String)`.

#### 🧪 Cliente RMI
- Se conecta a un servidor RMI y llama a `echo("Hola")`.

#### 🧪 Chat con RMI
- Dos aplicaciones cliente que publican sus objetos y se conectan entre sí para enviarse mensajes remotos.
- Se solicita al usuario:
  - Puerto para publicar
  - IP y puerto del otro cliente (opcional)
- **Clases**: `ChatInterface`, `ChatImpl`, `ChatApp`

---

## 🚀 Cómo Ejecutar los Ejercicios

Cada sección tiene su propio conjunto de clases. Para compilar y ejecutar:

```bash
# Compilar todo
javac *.java

# Ejecutar ejemplos individuales:
java EchoServer         # Servidor TCP
java EchoClient         # Cliente TCP
java ChatServer         # Servidor de chat con múltiples clientes
java ChatClient         # Cliente de chat
java URLReader          # Lector de páginas
java BrowserSaver       # Guarda HTML
java HttpServer         # Servidor Web
java DatagramTimeServer # Servidor UDP de hora
java DatagramTimeClient # Cliente UDP de hora
java ChatApp            # Chat RMI
````

⚠️ Para RMI recuerda iniciar el `rmiregistry` y tener configurado el archivo de seguridad (`policy`).

---

## 📁 Organización sugerida del proyecto

```
/JavaNetworkingLab
│
├── tcp/
│   ├── EchoServer.java
│   ├── EchoClient.java
│   └── ChatServer.java
│   └── ChatClient.java
│
├── udp/
│   ├── DatagramTimeServer.java
│   └── DatagramTimeClient.java
│
├── http/
│   ├── HttpServer.java
│   └── BrowserSaver.java
│
├── rmi/
│   ├── ChatInterface.java
│   ├── ChatImpl.java
│   └── ChatApp.java
│
├── url/
│   └── URLReader.java
│
└── README.md
```

---

## 🧾 Licencia

Este proyecto se distribuye bajo la licencia GNU GPL v3.
Incluye código y ejemplos de la documentación oficial de Oracle:
[https://docs.oracle.com/javase/tutorial/networking/index.html](https://docs.oracle.com/javase/tutorial/networking/index.html)

---

## 👨‍🏫 Autoría y Créditos

* Autor del taller: **Luis Daniel Benavides Navarro**
  Escuela Colombiana de Ingeniería Julio Garavito
* Desarrollo de ejercicios y mejoras: **Diego Chicuazuque [https://github.com/marzo245]**

---

## 📌 Recomendaciones

* Usa puertos superiores a 1024 para evitar conflictos.
* Asegúrate de tener abiertos los puertos en el firewall si trabajas en red local.
* Para el chat con RMI, puedes ejecutar ambos nodos en localhost para pruebas.

---

