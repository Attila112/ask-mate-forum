import { useState } from "react";

export default function SignUp() {
  const [userName, setUserName] = useState("");
  const [userPassword, setUserPassword] = useState("");

  const addUser = async (user) => {
    const response = await fetch("/api/users/", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(user),
    });
    return await response.json();
  };
  const loginUser = async (user) => {
    const response = await fetch("/api/users/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(user),
    });
    return await response.json();
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const user = {
      user_name: userName,
      user_password: userPassword,
    };
    try {
      await addUser(user);
      await loginUser(user);
      window.location.reload();
    } catch (error) {
      console.error("Error adding user:", error);
    }
  };

  return (
    <form onSubmit={handleSubmit} className="signup">
      <div>
        <label htmlFor="userName">New username</label>
        <input
          id="userName"
          value={userName}
          onChange={(e) => setUserName(e.target.value)}
        />
      </div>
      <div>
        <label htmlFor="userPassword">New password</label>
        <input
          id="userPassword"
          type="password"
          value={userPassword}
          onChange={(e) => setUserPassword(e.target.value)}
        />
      </div>
      <button type="submit">Sign Up</button>
    </form>
  );
}
