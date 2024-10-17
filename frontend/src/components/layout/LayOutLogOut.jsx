import {Link, Outlet} from "react-router-dom";
import './Layout.css'
const LayoutLogOut = () => {


    return (<div className="Layout">
        <nav>
            <ul>
                <li className="grow">
                    <Link to="/">
                        <button type="button">Home</button>
                    </Link>
                </li>
                <li>
                    <Link to="/questions">
                        <button type="button">Questions</button>
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

export default LayoutLogOut;