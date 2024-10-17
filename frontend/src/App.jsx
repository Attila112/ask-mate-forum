import { BrowserRouter, Routes, Route } from "react-router-dom";
import { useEffect, useState } from "react";
import Layout from "./components/layout/Layout.jsx";
import Home from "./components/pages/home/Home.jsx"
import AllQuestions from "./components/pages/question/AllQuestions.jsx";
import Users from "./components/pages/user/Users.jsx";
import AnswersByQuestion from "./components/pages/answer/AnswersByQuestion.jsx";
import AnswersByQuestionLogOut from "./components/pages/answer/AnswersByQuestionLogOut.jsx";
import CreateQuestion from "./components/pages/question/CreateQuestion.jsx";
import HomLogOut from "./components/pages/home/HomLogOut.jsx";
import LayoutLogOut from "./components/layout/LayOutLogOut.jsx";

export default function App() {
  const [user, setUser] = useState(null);
  useEffect(() => {
    const getUser = async () => {
      try {
        const response = await fetch(`/api/users/user`);
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        const text = await response.text();

        if (text) {
          const data = JSON.parse(text);
          setUser(data);
        } else {
          console.log("No data received");
          setUser(null);
        }
      } catch (error) {
        console.error("Failed to fetch user:", error);
      }
    };
    getUser();
  }, []);
  if (user != null) {
    return (
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Layout />}>
            <Route path="/" element={<Home />} />
            <Route path="/questions" element={<AllQuestions />} />
            <Route path="/users" element={<Users />} />
            <Route
              path="questions/:id"
              element={<AnswersByQuestion userId={user.id} />}
            />
            <Route path="create-question/" element={<CreateQuestion />} />
          </Route>
        </Routes>
      </BrowserRouter>
    );
  } else {
    return (
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<LayoutLogOut />}>
            <Route path="/" element={<HomLogOut />} />
            <Route path="questions" element={<AllQuestions />} />
            <Route path="/users" element={<Users />} />
            <Route path="questions/:id" element={<AnswersByQuestionLogOut />} />
          </Route>
        </Routes>
      </BrowserRouter>
    );
  }
}
