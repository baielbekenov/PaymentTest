# Payment API with JWT Authorization

This project provides a simple API for user authentication and payment processing, built with Spring Boot. The API features user registration, login, and secure payment functionality. JWT is used for authentication, allowing users to maintain multiple sessions.

## Features
- **User Registration**: Allows new users to register and create an account.
- **Login**: Users can log in with their credentials to receive a JWT token for authorization.
- **Logout**: Users can invalidate their token to log out.
- **Payment**: Authorized users can make payments, which deduct 1.1 USD from their balance on each request. The initial balance is set to 8 USD.
- **Session Management**: Supports multiple active sessions for users.
- **BigDecimal for Financial Data**: All transactions and balances use BigDecimal for precision and accuracy.

## Endpoints
- `POST /register`: Registers a new user and returns a JWT token.
- `POST /login`: Authenticates the user and returns a JWT token.
- `POST /logout`: Invalidates the current user's token.
- `GET /payment`: Processes a payment, deducting 1.1 USD from the user's balance.

## Documentation
- API documentation is provided using Swagger. You can access it at `/swagger-ui/index.html` after running the application.
