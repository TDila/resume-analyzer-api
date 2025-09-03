# AI-Powered Resume Analyzer REST API

---

## Project Overview

This is a **Spring Boot REST API** that can:

- Upload a PDF resume
- Extract text from the resume
- Compare the resume with a job description using AI (OpenAI GPT)
- Return results including:
  - Skill match percentage
  - Key matching skills
  - Missing skills
  - Job fit summary

This project shows how to use **Spring Boot, REST API, PDF parsing, and AI/NLP** together.

---

## Tech Stack

- **Backend:** Spring Boot
- **AI/NLP:** OpenAI GPT API
- **PDF Parsing:** Apache Tika
- **Java Libraries:** Jackson Databind, Apache Commons IO, Lombok
- **Build:** Maven
- **Security:** API key via `.env` file or application properties

---

## Setup

1. **Clone the repository**
git clone https://github.com/TDila/resume-analyzer-api
cd yourrepo


2. **Add OpenAI API key**
Create a `.env` file in the project root:
OPENAI_API_KEY=your_openai_api_key_here


3. **Run the project**
mvn clean install
mvn spring-boot:run

The API will run at `http://localhost:8080`

---

## API Endpoints

### 1. Upload Resume
- **URL:** `/api/resumes/upload`
- **Method:** `POST`
- **Body:** Form-data, key = `file` (PDF resume)

**Example Response:**
```json
{
  "resumeText": "Experienced Java developer with Spring Boot, REST API..."
}

### 2. Analyze Resume
- **URL:** `/api/resumes/analyze`
- **Method:** POST
- **Body:** JSON

```json
{
  "resumeText": "Extracted resume text from /upload",
  "jobDescription": "Looking for a Java Backend Developer skilled in Spring Boot, REST APIs, and MySQL"
}

**Example Response:**
```json
{
  "skillMatchPercentage": 80,
  "matchingSkills": [
    "Java (Advanced)",
    "Spring Boot (Basic)",
    "REST API development (Advanced)",
    "MySQL (Advanced)"
  ],
  "missingSkills": [
    "Docker (Beginner)",
    "Kubernetes (Beginner)",
    "CI/CD pipelines"
  ],
  "summary": "The candidate's skills in Java, REST API development, and databases match well with the job requirements..."
}
