import React from 'react'
import ReactDOM from 'react-dom/client'
import './index.css'
import {createBrowserRouter} from "react-router-dom";
import Layout from "./components/layout/Layout.jsx";
import Home from "./components/pages/home/Home.jsx";
import AllQuestions from "./components/pages/question/AllQuestions.jsx";
import Users from "./components/pages/user/Users.jsx";
import AnswersByQuestion from "./components/pages/answer/AnswersByQuestion.jsx";
import CreateQuestion from "./components/pages/question/CreateQuestion.jsx";
import App from "./App.jsx";

const router = createBrowserRouter([
    {
        path: '/',
        element: <Layout />,
        children: [
            {
                path: '/',
                element: <Home />,
            },
            {
                path: '/questions',
                element: <AllQuestions/>,
            },
            {
                path: '/users',
                element: <Users/>,
            },
            {
                path: '/questions/:id',
                element: <AnswersByQuestion />,
            },
            {
                path: '/create-question/',
                element: <CreateQuestion/>,
            },
        ]

    }
])

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <React.StrictMode>
        <App></App>
    </React.StrictMode>
);
