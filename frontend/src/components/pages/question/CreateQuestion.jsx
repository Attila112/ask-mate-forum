import {useEffect, useState} from "react";

export default function CreateQuestion() {
    const [questionTitle, setQuestionTitle] = useState("");
    const [questionDescription, setQuestionDescription] = useState("");
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

    const addQuestion = async (question) => {
        const response = await fetch("/api/question/", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(question),
        });
        return await response.json();
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const question = {
            title: questionTitle,
            description: questionDescription,
            user_id: user.id,
        };
        try {
            return await addQuestion(question);
        } catch (error) {
            console.log(error);
        }
    };

    return (
        <form onSubmit={handleSubmit} className="create">
            <div>
                <h1>Create a new Question!</h1>
                <label htmlFor='questionTitle'>Add title</label>
                <input
                    id='questionTitle'
                    value={questionTitle}
                    onChange={(e) => setQuestionTitle(e.target.value)}
                />
            </div>
            <div>
                <label htmlFor='questionDescription'>Your question</label>
                <input
                    id='questionDescription'
                    type="text"
                    value={questionDescription}
                    onChange={(e) => setQuestionDescription(e.target.value)}
                />
            </div>
            <button type="submit">Submit Question</button>
        </form>
    );
}