import Login from "./Login.jsx";
import SignUp from "./SignUp.jsx";

export default function HomLogOut(){
    return(
        <div className="out">
        <h1>Pls log in to our site</h1>
            <Login/>
            <h1>Or Sign Up!</h1>
            <SignUp/>
        </div>
    )
}