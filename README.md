# AI Chatbot 🤖

## About
AI Chatbot is an intelligent chat application designed to facilitate seamless communication between users and an AI assistant. Built with Angular for the frontend and Spring Boot for the backend, this chatbot provides real-time responses, chat history management, and a smooth user experience. With Docker support, it is easy to deploy and scale.

## Features 🚀

- 🎨 User-friendly interface with Angular 18
- 🤖 AI-powered responses using OpenAI or other NLP models
- 📜 Chat history management
- 🏗️ Backend built with Spring Boot for handling requests
- 🐳 Docker support for deployment

## Tech Stack 🛠️

### Frontend:

- 🅰️ Angular 18
- 🟦 TypeScript
- 🎨 HTML, CSS

### Backend:

- ☕ Spring Boot
- 🖥️ Java
- 🗄️ MySQL (Database)

### Deployment:

- 🐳 Docker
- 📦 Docker Compose
- 🌐 Nginx (for serving Angular frontend)

## Project Structure 📂

```
├── frontend/           # Angular frontend
├── backend/            # Spring Boot backend
├── Dockerfile          # Dockerfile for containerization
├── docker-compose.yml  # Docker Compose configuration
```

## Setup & Installation ⚙️

### Prerequisites 📝

- 🟢 Node.js and npm (for frontend)
- ☕ Java 11 or later (for backend)
- 🗄️ MySQL (Database)
- 🐳 Docker (for containerization)

### Steps to Run Locally 💻

1. Clone the repository:

   ```sh
   git clone https://github.com/your-username/AI_Chatbot.git
   cd AI_Chatbot
   ```

2. **Backend Setup:**

   ```sh
   cd backend
   mvn clean install
   mvn spring-boot:run
   ```

3. **Frontend Setup:**

   ```sh
   cd ../frontend
   npm install
   ng serve
   ```

4. Open your browser and go to:

   ```
   http://localhost:4000
   ```

### Running with Docker 🐳

1. Build and start the containers:

   ```sh
   docker-compose up --build
   ```

2. Access the application at:
   ### Frontend
      ```
      http://localhost:4000
      ```
   
   ### Backend
      ```
      http://localhost:8080
      ```

## API Endpoints 🌐

### User Authentication

- 🔹 `POST /auth/register` - Registers a user
- 🔹 `POST /auth/login` - Login to application

### Chat API

- 🔹 `POST /chat` - Sends a user query to the AI 
- 🔹 `GET /chat/history` - Retrieves chat history of the authenticated user.
- 🔹 `GET /conversations/{model}` - Retrives chat models
- 🔹 `GET conversations/id?conversationId=1` - Retrieves a conversation by its unique ID.
- 🔹 `GET /conversations` - Retrives all previous conversations

## Contributors 👨‍💻👩‍💻

We appreciate all contributions to this project! Special thanks to:

- [@om-hub98](https://github.com/om-hub98) - Project Owner 👑
- [@Blank-UV](https://github.com/Blank-UV) - Project Owner 👑

## Contributing 🤝

Pull requests are welcome! Please follow the standard Git workflow:

1. 🍴 Fork the repository
2. 🌿 Create a feature branch (`git checkout -b feature-name`)
3. 📝 Commit your changes (`git commit -m "Added feature"`)
4. 🔄 Push to the branch (`git push origin feature-name`)
5. 📩 Open a pull request

## Contact 📬

For any inquiries, feel free to reach out:

### Omraj Pradhan
- 📧 Email: [omrajpradhan98@gmail.com](mailto:omrajpradhan98@gmail.com)
- 🐙 GitHub: [om-hub98](https://github.com/om-hub98)

### Yuvaraj Pradhan
- 📧 Email: [pradhanyuvaraj98@gmail.com](mailto:pradhanyuvaraj98@gmail.com)
- 🐙 GitHub: [Blank-UV](https://github.com/Blank-UV)

