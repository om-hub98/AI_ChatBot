# AI Chatbot

An AI-powered chatbot application built using Angular for the frontend and Spring Boot for the backend. This chatbot allows users to interact with AI and maintain a history of conversations.

## Features

- User-friendly interface with Angular 22
- AI-powered responses using OpenAI or other NLP models
- Chat history management
- Backend built with Spring Boot for handling requests
- Docker support for deployment

## Tech Stack

### Frontend:

- Angular 18
- TypeScript
- HTML, CSS

### Backend:

- Spring Boot
- Java
- MySQL (Database)

### Deployment:

- Docker
- Docker Compose
- Nginx (for serving Angular frontend)

## Project Structure

```
├── frontend/           # Angular frontend
├── backend/            # Spring Boot backend
├── Dockerfile          # Dockerfile for containerization
├── docker-compose.yml  # Docker Compose configuration
```

## Setup & Installation

### Prerequisites

- Node.js and npm (for frontend)
- Java 11 or later (for backend)
- MySQL (Database)
- Docker (for containerization)

### Steps to Run Locally

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
   http://localhost:4200
   ```

### Running with Docker

1. Build and start the containers:

   ```sh
   docker-compose up --build
   ```

2. Access the application at:

   ```
   http://localhost:8080
   ```

## API Endpoints

### Chat API

- `POST /chat` - Send a message to the AI chatbot
- `GET /chat/history` - Retrieve chat history

## Contributing

Pull requests are welcome! Please follow the standard Git workflow:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature-name`)
3. Commit your changes (`git commit -m "Added feature"`)
4. Push to the branch (`git push origin feature-name`)
5. Open a pull request


