import {Link, Outlet} from "react-router-dom";
import './Layout.css'
const Layout = () => {
    const handleLogOut = async () => {
        const response = await fetch("/api/users/logout", {
            method: "POST",
            headers: {'Content-Type': 'application/json'}
        });
        window.location.reload()
    }
   return( <div className="Layout">
        <nav>
            <ul>
                <li className="grow">
                    <Link to="/">
                        <button type="button">Home</button>
                    </Link>
                </li>
                <li>
                    <Link to="/">
                        <button type="button" onClick={() => handleLogOut()}>Log out</button>
                    </Link>
                </li>
                <li>
                    <Link to="/questions">
                        <button type="button">Questions</button>
                    </Link>
                </li>
                <li>
                    <Link to="/create-question">
                        <button type="button">Ask Question</button>
                    </Link>
                </li>
                <li>
                    <Link to="/users">
                        <button type="button">Users</button>
                    </Link>
                </li>
            </ul>
        </nav>
        <Outlet/>
    </div>
)
};

export default Layout;