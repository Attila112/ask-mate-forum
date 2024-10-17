import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

export default function AllQuestions() {
  const [allQuestions, setAllQuestions] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const getAllQuestions = async () => {
      const response = await fetch("/api/question/all");
      const data = await response.json();
      setAllQuestions(data);
    };
    getAllQuestions();
  }, []);

  return (
    <div className="questions">
      <h1>Questions:</h1>
      {allQuestions?.map((q) => (
        <h2 key={q.id} onClick={() => navigate(`/questions/${q.id}`)}>
          {q.title}
        </h2>
      ))}
    </div>
  );
}
