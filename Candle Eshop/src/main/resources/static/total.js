


class Totalrenderr extends React.Component {

    render() {

        /*alert(this.props.boom + "String" + this.props.flag);*/


        return (
            <div>
                <table  className="table">
                    <thead>
                    </thead>
                    <tbody>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>

                        <td colSpan={6} ><h4>Total: {this.props.total} €</h4></td>
                    </tr>
                    </tbody>
                    <tfoot></tfoot>
                </table>

            </div>
        );
    }
}



class Main extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            total: []
        }
    }
    componentDidMount() {
        fetch("/calculateTotal")
            .then(res => res.json())
            .then(
                (response) => {
                    this.setState({
                        total: response,
                    });
                },
                (error) => {
                    alert(error);
                }
            )
    }

    render() {
        // alert("return main")
        return (
            <div id="main">
                <Totalrenderr total={this.state.total}  />
            </div>
        );
    }
}




ReactDOM.render(<Main />,document.getElementById("total_root"));
