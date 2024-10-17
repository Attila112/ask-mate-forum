import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import CreateAnswer from "./CreateAnswer.jsx";

export default function AnswersByQuestion(props) {
  const { id } = useParams();
  const [question, setQuestion] = useState(null);
  const [showAdd, setShowAdd] = useState(false);
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

  const fetchAnswers = async (qId) => {
    const response = await fetch(`/api/answer/question/${qId}`);
    if (!response.ok) {
      throw new Error("Failed to fetch answers");
    }
    const data = await response.json();
    setAnswers(data);
  };

  useEffect(() => {
    fetchAnswers(id);
  }, [id]);

  const deleteFetchAnswer = async (id) => {
    await fetch(`/api/answer/${id}`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
    });
    window.location.reload();
  };

  return (
    <div className="answer">
      <h2>{question?.title}</h2>
      <p>{question?.description}</p>
      <div>
        <button onClick={() => setShowAdd(!showAdd)}>Answer</button>
      </div>
      <h4>Answers: </h4>
      {answers?.map((a) => (
        <div key={a.id}>
          <h3>
            {a.description}
            {a.user_id === props.userId ? (
              <button onClick={() => deleteFetchAnswer(a.id)}> X</button>
            ) : (
              ""
            )}
          </h3>
        </div>
      ))}
      {showAdd && <CreateAnswer id={id} />}
    </div>
  );
}
