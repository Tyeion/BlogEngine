                                                                       ****BlogEngine Backend (Spring Boot)****
This project is a backend service for a blogging platform, built using Java, Spring Boot, and Hibernate. It provides a robust foundation for managing users, posts, and comments with full CRUD capabilities and proper entity relationships. Currently, it's a backend-only application and will later be connected to a frontend (likely using React with Render deployment).

                                                                        🚀 Features
Full CRUD operations for:
Users
Posts
Comments
Entity Relationship Mapping using Hibernate:
One-to-Many and Many-to-One mappings between Users, Posts, and Comments
DTOs (Data Transfer Objects) for clean API response handling
DAOs and Utility Classes for organized business logic
RESTful API architecture via Spring Boot controllers

🧩 Tech Stack
Java 17+
Spring Boot
Hibernate / JPA
Maven
MySQL (or your configured DB)


Endpoints Overview
POST /api/users - Create a new user
GET /api/users/{id} - Get user by ID
POST /api/posts - Create a new post
GET /api/posts/{id} - Get post by ID
POST /api/comments - Add a comment to a post
GET /api/comments/{id} - Get comment by ID

🛣️ Roadmap
✅ Implement backend with full CRUD
✅ Map entity relationships (User ↔ Post ↔ Comment)
🧾 Postman/OpenAPI Documentation


