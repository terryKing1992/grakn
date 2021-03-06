const grpc = require("grpc");
const services = require("./autogenerated/Session_grpc_pb");
const TxService = require("./TransactionService");


/**
 * This creates a new connection to the server over HTTP2,
 * the connection will contain all the Transaction streams
 */
function SessionService(uri, keyspace, credentials) {
    this.keyspace = keyspace;
    this.credentials = credentials;
    this.stub = new services.SessionServiceClient(uri, grpc.credentials.createInsecure());
}

/**
 * This method creates a new Duplex Stream (this.stub.transaction()) over which gRPC will communicate when
 * exchanging messages related to the Transaction service.
 * It also sends an Open request before returning the TransactionService
 * @param {Grakn.txType} txType type of transaction to be open
 */
SessionService.prototype.transaction = async function create(txType) {
    const txService = new TxService(this.stub.transaction());
    await txService.openTx(this.keyspace, txType, this.credentials);
    return txService;
}

/**
 * Closes connection to the server
 */
SessionService.prototype.close = function close() {
    grpc.closeClient(this.stub);
}

module.exports = SessionService;