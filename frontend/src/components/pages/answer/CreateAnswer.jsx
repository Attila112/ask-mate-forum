import {useEffect, useState} from "react";

export default function CreateAnswer({id}) {
    const [answerText, setAnswerText] = useState('');
    async function fetchUserLogIn() {
        const user = await fetch("/api/users/user");
        const data = await user.json();
        return data
    }
    const [user, setUser] = useState(null);
    useEffect(() => {
        fetchUserLogIn().then((res) => {
            setUser(res)
        });
    }, [])

    const addAnswer = async (answer) => {
        const response = await fetch(`/api/answer/`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(answer),
        })
        return await response.json();
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        const answer = {
            description: answerText,
            question_id: id,
            user_id: user.id,
        }
        await addAnswer(answer);
        window.location.reload()
    }

    return <form onSubmit={handleSubmit}>
        <div>
            <label htmlFor='answer'>Add title</label>
            <input
                id='answer'
                value={answerText}
                onChange={(e) => setAnswerText(e.target.value)}
            />
        </div>
        <button type="submit">Submit Answer</button>
    </form>
}