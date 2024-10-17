# Ask-Mate!  (forum)

This is a forum application with a React frontend and a Java Spring backend. This application allows users to post new questions and provide answers to others' questions, fostering community engagement and knowledge exchange. The combination of React for a dynamic user interface and Java Spring for robust backend support ensures a seamless and scalable experience. The platform encourages knowledge sharing and interaction by allowing users to discuss various topics through questions and answers.

## Table of Contents
- [Ask-Mate!  (forum)](#ask-mate--forum)
  - [Table of Contents](#table-of-contents)
  - [Project Overview](#project-overview)
  - [Features](#features)
    - [Built With](#built-with)
  - [Installation](#installation)
    - [Installation without Docker](#installation-without-docker)
  - [Usage](#usage)
  - [Contributors](#contributors)
  - [License](#license)

---

## Project Overview

This project was developed by a team of three developers. The application also allows users to browse and search for specific topics, ensuring easy access to relevant information. It promotes collaboration by encouraging open discussions and knowledge sharing.

---

## Features

- **Question Posting**: Users can create new discussion topics by posting questions.
- **Answering Questions**: Users can respond to questions posted by others, providing answers or opinions.
- **User Profiles**: Each user has a profile displaying their activity, including posted questions and answers.

---
### Built With

* [![React][React.js]][React-url]
* [![SpringBoot][Springboot-shield]][Springboot-url]
* [![PostgreSQL][POSTGRESQL-shield]][POSTGRESQL-url]
* [![Docker][Docker-shield]][Docker-url]

---

## Installation
### Installation without Docker
1. Clone the repository
   ```sh
   git clone https://github.com/Attila112/ask-mate-forum.git
   ```
2. Installations:
   Install a version of JDK
   
   Install IDE
   Install VSCode
   Install Node.js
   Install postgreSQL
4. Environmental variables
   Provide your environmental variables(DB_USERNAME, DB_PASSWORD, SECRET_KEY, DB_URL) in the application.properties file which is in the backend/src/main/resources folder. 
5. Run frontend
   In VSCode, navigate to the frontend folder and run:
   ```sh
   npm install
   ```
   Then run:
   ```sh
   npm run dev
   ```
   And now your frontend is running.
5. Run backend
   Open the backend folder in IDE and start the application.

6. Let's see the application in: http://localhost:5173

----------

## Usage

1. **Login**: You can login to the Web page if you have a real account.
2. **Sign In**: If you don't have validate user profile you must to Sign In our application.
3. **Add new question**: You can post a new question and wait for the answers.
4. **Write answer**: If you found a question that you feel you know the answer to, send it to the questioner
5. **See users**: On the users page you see the all user who register our application.

----------

## Contributors

-   **Attila Gonda** - [@Attila112](https://github.com/Attila112)
-   **Zoltán Varga** - [@NuubShybot](https://github.com/Nuubshybot)
-   **Attila Farkas** - [@attis82](https://github.com/attis82)

## License
This project is licensed under the MIT License.


[React.js]: https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB
[React-url]: https://reactjs.org/
[POSTGRESQL-shield]:https://img.shields.io/badge/postgresql-4169e1?style=for-the-badge&logo=postgresql&logoColor=white
[POSTGRESQL-url]:https://www.postgresql.org/
[Docker-shield]:https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white
[Docker-url]:https://www.docker.com/
[Springboot-shield]:https://img.shields.io/badge/SpringBoot-6DB33F?style=flat-square&logo=Spring&logoColor=white
[Springboot-url]:https://spring.io/projects/spring-boot