import { useEffect, useState } from "react";

export default function Users() {
  const [users, setUsers] = useState(null);

  useEffect(() => {
    const getAllUsers = async () => {
      const response = await fetch("/api/users/all");
      const data = await response.json();
      setUsers(data);
    };
    getAllUsers();
  }, []);

  return (
    <div className="users">
      <h1>Our Users: </h1>
      {users?.map((user) => (
        <h2 key={user.user_name}>{user.user_name}</h2>
      ))}
    </div>
  );
}
