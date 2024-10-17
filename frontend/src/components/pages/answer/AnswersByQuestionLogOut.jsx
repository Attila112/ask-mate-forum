import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";

export default function AnswersByQuestionLogOut() {
  const { id } = useParams();
  const [question, setQuestion] = useState(null);
  const [answers, setAnswers] = useState(null);

  useEffect(() => {
    const getQuestion = async () => {
      try {
        const response = await fetch(`/api/question/${id}`);
        const data = await response.json();
        setQuestion(data);
      } catch (error) {
        console.error(error.message);
      }
    };
    getQuestion();
  }, [id]);

  const fetchAnswers = async () => {
    const response = await fetch(`/api/answer/question/${id}`);
    if (!response.ok) {
      throw new Error("Failed to fetch answers");
    }
    const data = await response.json();
    setAnswers(data);
  };

  useEffect(() => {
    fetchAnswers();
  }, [id]);

  return (
    <div className="answer">
      <h2>{question?.title}</h2>
      <p>{question?.description}</p>
      <h4>Answers: </h4>
      {answers?.map((a) => (
        <div key={a.id}>
          <h3>{a.description}</h3>
        </div>
      ))}
    </div>
  );
}
