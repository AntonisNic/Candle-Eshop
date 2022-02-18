
class DeleteButton extends React.Component{
    constructor(props) {
        super(props);
    }
    render(){

        return (
            <form action="/deleteproduct" method="get">
                <input name="basketID" type="hidden" value={this.props.basketID} readOnly/>
                <button type="submit" className="btn btn-danger"> DELETE</button>
            </form>
        )
    };
}


class ProductList extends React.Component {

    render() {

        if (!this.props.products) {
            return <div className="temp">No products yet...</div>
        }
        return (
            <div>
                <div className="temp">
                    You have {this.props.products.length} products in your basket
                </div>
                <table id="question-list" className="table" >
                    <thead>

                    <tr>
                        <th >Product</th>
                        <th >Color</th>
                        <th >Price €</th>
                    </tr>
                    </thead>
                    <tbody>
                    {this.props.products.map((product) => (

                        <tr>
                            <td> {product.name}</td>
                            <td> {product.color}</td>
                            <td>{product.price}</td>
                            <td><DeleteButton basketID={product.basketID}/></td>

                        </tr>

                    ))}
                    </tbody>
                </table>
            </div>
        );
    }
}







class Main extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            products: []
        }
    }
    componentDidMount() {
        fetch("/basket")
            .then(res => res.json())
            .then(
                (response) => {
                    this.setState({
                        products: response
                    });
                },
                (error) => {
                    alert(error);
                }
            )
    }

    render() {
        return (
            <div id="main">
                <ProductList products={this.state.products}/>
            </div>
        );
    }
}











ReactDOM.render(
    <Main />,
    document.getElementById("basket_id")
);
