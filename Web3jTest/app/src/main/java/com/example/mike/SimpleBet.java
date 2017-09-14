package com.example.mike;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint16;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.utils.Async;

import rx.Observable;
import rx.functions.Func1;

/**
 * Auto generated code.<br>
 * <strong>Do not modify!</strong><br>
 * Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>, or {@link org.web3j.codegen.SolidityFunctionWrapperGenerator} to update.
 *
 * <p>Generated with web3j version 2.3.1.
 */
public final class SimpleBet extends Contract {
    private static final String BINARY = "60606040525b600a8054600160a060020a03191633600160a060020a0316179055620000386401000000006200003f810262000dcf1704565b5b62000498565b604080519081016040528060015b8152602001620151806200007a6107e16009601e600e60008064010000000062000a206200020182021704565b0390526205cdd860005260086020527f64287aa1200fdb1c967415c3978c1f52f71ffdb05a53d6e9ce36c371b475883b81518154829060ff19166001836005811115620000c357fe5b0217905550602082015160019091015550604080519081016040528060015b8152602001620151806200010f6107e160096010600e60008064010000000062000a206200020182021704565b0390526205cdcd60005260086020527f96afc84444773f597f175f88f976e96cddd3a6a59b58e449e96bbbb7f66b413681518154829060ff191660018360058111156200015857fe5b0217905550602082015160019091015550604080519081016040528060015b815260200162015180620001a46107e16009601e600e60008064010000000062000a206200020182021704565b0390526205cdd460005260086020527f64352a668e0ededad9b05cb0df8a948e44ed3b1c5b531e5bdef4e32736160edd81518154829060ff19166001836005811115620001ed57fe5b02179055506020820151600190910155505b565b6000806200020e6200046e565b6107b291505b8861ffff168261ffff1610156200026a576200023e8264010000000062000c026200040e82021704565b1562000253576301e28500830192506200025d565b6301e13380830192505b5b60019091019062000214565b601f8160005b60ff9092166020929092020152620002968964010000000062000c026200040e82021704565b15620002b557601d8160015b60ff9092166020929092020152620002c9565b601c8160015b60ff90921660209290920201525b601f8160025b60ff9092166020929092020152601e8160035b60ff9092166020929092020152601f8160045b60ff9092166020929092020152601e8160055b60ff9092166020929092020152601f8160065b60ff9092166020929092020152601f8160075b60ff9092166020929092020152601e8160085b60ff9092166020929092020152601f8160095b60ff9092166020929092020152601e81600a5b60ff9092166020929092020152601f81600b5b60ff9092166020929092020152600191505b8760ff168261ffff161015620003cf578061ffff600019840116600c8110620003b157fe5b602002015160ff166201518002830192505b6001909101906200038c565b6001870360ff166201518002830192508560ff16610e1002830192508460ff16603c02830192508360ff16830192508292505b50509695505050505050565b6000600461ffff83165b0661ffff16156200042c5750600062000469565b606461ffff83165b0661ffff1615620004485750600162000469565b61019061ffff83165b0661ffff1615620004655750600062000469565b5060015b919050565b610180604051908101604052600c815b6000815260001990910190602001816200047e5790505090565b610f8a80620004a86000396000f300606060405236156100b75763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166327dc297e81146100bc578063364e69b71461011457806338bbfa501461013e5780633ccfd60b146101d85780634768d4ef146101ed5780634bd5b1351461022e5780634c36c36e1461025c5780636c0c27e1146102745780639054bdec14610281578063a6f0e577146102cb578063f3f43703146102f9578063ff7470951461032a575b600080fd5b34156100c757600080fd5b610112600480359060446024803590810190830135806020601f8201819004810201604051908101604052818152929190602084018383808284375094965061034c95505050505050565b005b341561011f57600080fd5b61012a60043561037c565b604051901515815260200160405180910390f35b341561014957600080fd5b610112600480359060446024803590810190830135806020601f8201819004810201604051908101604052818152929190602084018383808284378201915050505050509190803590602001908201803590602001908080601f0160208091040260200160405190810160405281815292919060208401838380828437509496506103df95505050505050565b005b34156101e357600080fd5b6101126103e5565b005b34156101f857600080fd5b610203600435610431565b6040518083600581111561021357fe5b60ff1681526020018281526020019250505060405180910390f35b61024a600160a060020a03600435166024356044351515610450565b60405190815260200160405180910390f35b341561026757600080fd5b6101126004356106c3565b005b610112600435610919565b005b341561028c57600080fd5b61024a61ffff6004351660ff60243581169060443581169060643581169060843581169060a43516610a20565b60405190815260200160405180910390f35b34156102d657600080fd5b61012a61ffff60043516610c02565b604051901515815260200160405180910390f35b341561030457600080fd5b61024a600160a060020a0360043516610c5c565b60405190815260200160405180910390f35b341561033557600080fd5b61011260043560243515156044351515610c6e565b005b610377828260006040518059106103605750595b908082528060200260200182016040525b506103df565b5b5050565b600081815260086020526040812060015b815460ff16600581111561039d57fe5b146103ab57600091506103d9565b42816001015411156103d4578054600290829060ff19166001835b0217905550600091506103d9565b600191505b50919050565b5b505050565b600160a060020a033316600081815260096020526040808220805492905590919082156108fc0290839051600060405180830381858888f19350505050151561042d57600080fd5b5b50565b6008602052600090815260409020805460019091015460ff9091169082565b600061045b8361037c565b15156104735761046b3334610d3c565b5060006106bc565b811561056d5760a06040519081016040528060015b815260200184815260200133600160a060020a0316815260200185600160a060020a031681526020013481525060066000600554815260200190815260200160002060008201518154829060ff191660018360048111156104e557fe5b021790555060208201518160010155604082015160028201805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0392909216919091179055606082015160038201805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a039290921691909117905560808201516004909101555061065d565b60a06040519081016040528060025b815260200184815260200185600160a060020a0316815260200133600160a060020a031681526020013481525060066000600554815260200190815260200160002060008201518154829060ff191660018360048111156105d957fe5b021790555060208201518160010155604082015160028201805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0392909216919091179055606082015160038201805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a03929092169190911790556080820151600490910155505b7f36acced6b449c5b64c4e22b0e5f06624666c1f5aae7364d74635dc67671890f23385600554604051600160a060020a039384168152919092166020820152604080820192909252606001905180910390a15060058054600181019091555b9392505050565b60008181526006602052604081209060015b825460ff1660048111156106e557fe5b1480610701575060025b825460ff1660048111156106ff57fe5b145b8061071c575060035b825460ff16600481111561071a57fe5b145b151561072757600080fd5b506001810154600090815260086020526040902060025b815460ff16600581111561074e57fe5b148061076a575060035b815460ff16600581111561076857fe5b145b80610785575060045b815460ff16600581111561078357fe5b145b806107a0575060055b815460ff16600581111561079e57fe5b145b15156107ab57600080fd5b60015b825460ff1660048111156107be57fe5b14156107e657600282015460048301546107e191600160a060020a031690610d3c565b6108fb565b60025b825460ff1660048111156107f957fe5b141561082157600382015460048301546107e191600160a060020a031690610d3c565b6108fb565b60035b815460ff16600581111561083457fe5b14156108605760028083015460048401546107e192600160a060020a039092169102610d3c565b6108fb565b60045b815460ff16600581111561087357fe5b14156108b8576002820154600483015461089691600160a060020a031690610d3c565b600382015460048301546107e191600160a060020a031690610d3c565b6108fb565b60055b815460ff1660058111156108cb57fe5b14156108f657600382015460048301546107e191600160a060020a031690600202610d3c565b6108fb565b6103df565b5b5b5b5b8154600490839060ff19166001835b02179055505b505050565b600081815260066020526040902060015b815460ff16600481111561093a57fe5b1480156109575750600381015433600160a060020a039081169116145b8061098d575060025b815460ff16600481111561097057fe5b14801561098d5750600281015433600160a060020a039081169116145b5b151561099957600080fd5b600481015434146109a957600080fd5b6109b6816001015461037c565b15156109d4576109c63334610d3c565b6109cf826106c3565b610377565b8054600390829060ff19166001835b02179055507f96b59cd77cc6cd91f51b84538f697b4e8d478750917ead4ef8a517920e3657f58260405190815260200160405180910390a15b5050565b600080610a2b610da6565b6107b291505b8861ffff168261ffff161015610a7357610a4a82610c02565b15610a5d576301e2850083019250610a67565b6301e13380830192505b5b600190910190610a31565b601f8160005b60ff9092166020929092020152610a8f89610c02565b15610aac57601d8160015b60ff9092166020929092020152610ac0565b601c8160015b60ff90921660209290920201525b601f8160025b60ff9092166020929092020152601e8160035b60ff9092166020929092020152601f8160045b60ff9092166020929092020152601e8160055b60ff9092166020929092020152601f8160065b60ff9092166020929092020152601f8160075b60ff9092166020929092020152601e8160085b60ff9092166020929092020152601f8160095b60ff9092166020929092020152601e81600a5b60ff9092166020929092020152601f81600b5b60ff9092166020929092020152600191505b8760ff168261ffff161015610bc3578061ffff600019840116600c8110610ba657fe5b602002015160ff166201518002830192505b600190910190610b83565b6001870360ff166201518002830192508560ff16610e1002830192508460ff16603c02830192508360ff16830192508292505b50509695505050505050565b6000600461ffff83165b0661ffff1615610c1e57506000610c57565b606461ffff83165b0661ffff1615610c3857506001610c57565b61019061ffff83165b0661ffff1615610c5357506000610c57565b5060015b919050565b60096020526000908152604090205481565b600a5433600160a060020a03908116911614610c8957600080fd5b60015b60008481526008602052604090205460ff166005811115610ca957fe5b14610cb357600080fd5b8115610cdf57600083815260086020526040902080546004919060ff19166001835b02179055506103df565b8015610d0f57600083815260086020526040902080546003919060ff1916600183610cd5565b02179055506103df565b600083815260086020526040902080546005919060ff191660018361090e565b02179055505b5b5b505050565b600160a060020a038216600090815260096020526040908190208054830190557f5d0ffc8366c3e98d84af46a4f8c32cd8e06dcaa97823881fd259a845891fc806908390839051600160a060020a03909216825260208201526040908101905180910390a15b5050565b610180604051908101604052600c815b600081526000199091019060200181610db65790505090565b604080519081016040528060015b815260200162015180610dfa6107e16009601e600e600080610a20565b0390526205cdd860005260086020527f64287aa1200fdb1c967415c3978c1f52f71ffdb05a53d6e9ce36c371b475883b81518154829060ff19166001836005811115610e4257fe5b0217905550602082015160019091015550604080519081016040528060015b815260200162015180610e7e6107e160096010600e600080610a20565b0390526205cdcd60005260086020527f96afc84444773f597f175f88f976e96cddd3a6a59b58e449e96bbbb7f66b413681518154829060ff19166001836005811115610ec657fe5b0217905550602082015160019091015550604080519081016040528060015b815260200162015180610f026107e16009601e600e600080610a20565b0390526205cdd460005260086020527f64352a668e0ededad9b05cb0df8a948e44ed3b1c5b531e5bdef4e32736160edd81518154829060ff19166001836005811115610f4a57fe5b02179055506020820151600190910155505b5600a165627a7a723058207ec2e49278b39b6fa308c4a44b25e3526cb1bcdb1a09477911c8db78074c611a0029";

    private SimpleBet(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private SimpleBet(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<BetProposedEventResponse> getBetProposedEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("BetProposed", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<BetProposedEventResponse> responses = new ArrayList<BetProposedEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            BetProposedEventResponse typedResponse = new BetProposedEventResponse();
            typedResponse.proposedBy = (Address) eventValues.getNonIndexedValues().get(0);
            typedResponse.against = (Address) eventValues.getNonIndexedValues().get(1);
            typedResponse.betId = (Uint256) eventValues.getNonIndexedValues().get(2);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<BetProposedEventResponse> betProposedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("BetProposed", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, BetProposedEventResponse>() {
            @Override
            public BetProposedEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                BetProposedEventResponse typedResponse = new BetProposedEventResponse();
                typedResponse.proposedBy = (Address) eventValues.getNonIndexedValues().get(0);
                typedResponse.against = (Address) eventValues.getNonIndexedValues().get(1);
                typedResponse.betId = (Uint256) eventValues.getNonIndexedValues().get(2);
                return typedResponse;
            }
        });
    }

    public List<BetAcceptedEventResponse> getBetAcceptedEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("BetAccepted", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<BetAcceptedEventResponse> responses = new ArrayList<BetAcceptedEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            BetAcceptedEventResponse typedResponse = new BetAcceptedEventResponse();
            typedResponse.betId = (Uint256) eventValues.getNonIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<BetAcceptedEventResponse> betAcceptedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("BetAccepted", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, BetAcceptedEventResponse>() {
            @Override
            public BetAcceptedEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                BetAcceptedEventResponse typedResponse = new BetAcceptedEventResponse();
                typedResponse.betId = (Uint256) eventValues.getNonIndexedValues().get(0);
                return typedResponse;
            }
        });
    }

    public List<WithdrawalIncreasedEventResponse> getWithdrawalIncreasedEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("WithdrawalIncreased", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<WithdrawalIncreasedEventResponse> responses = new ArrayList<WithdrawalIncreasedEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            WithdrawalIncreasedEventResponse typedResponse = new WithdrawalIncreasedEventResponse();
            typedResponse.adr = (Address) eventValues.getNonIndexedValues().get(0);
            typedResponse.value = (Uint256) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<WithdrawalIncreasedEventResponse> withdrawalIncreasedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("WithdrawalIncreased", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, WithdrawalIncreasedEventResponse>() {
            @Override
            public WithdrawalIncreasedEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                WithdrawalIncreasedEventResponse typedResponse = new WithdrawalIncreasedEventResponse();
                typedResponse.adr = (Address) eventValues.getNonIndexedValues().get(0);
                typedResponse.value = (Uint256) eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public Future<TransactionReceipt> __callback(Bytes32 myid, Utf8String result) {
        Function function = new Function("__callback", Arrays.<Type>asList(myid, result), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> canBet(Uint256 matchId) {
        Function function = new Function("canBet", Arrays.<Type>asList(matchId), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> __callback(Bytes32 myid, Utf8String result, DynamicBytes proof) {
        Function function = new Function("__callback", Arrays.<Type>asList(myid, result, proof), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> withdraw() {
        Function function = new Function("withdraw", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<List<Type>> matches(Uint256 param0) {
        Function function = new Function("matches", 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}, new TypeReference<Uint256>() {}));
        return executeCallMultipleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> makeBet(Address against, Uint256 matchId, Bool betOnHome, final BigInteger weiValue) {
        final Function function = new Function("makeBet", Arrays.<Type>asList(against, matchId, betOnHome), Collections.<TypeReference<?>>emptyList());
        return Async.run(new Callable<TransactionReceipt>() {
            @Override
            public TransactionReceipt call() throws Exception {
                return executeTransaction(FunctionEncoder.encode(function), weiValue);
            }
        });
    }

    public Future<TransactionReceipt> resolveBet(Uint256 betId) {
        Function function = new Function("resolveBet", Arrays.<Type>asList(betId), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> acceptBet(Uint256 betId, final BigInteger weiValue) {
        final Function function = new Function("acceptBet", Arrays.<Type>asList(betId), Collections.<TypeReference<?>>emptyList());
        return Async.run(new Callable<TransactionReceipt>() {
            @Override
            public TransactionReceipt call() throws Exception {
                return executeTransaction(FunctionEncoder.encode(function), weiValue);
            }
        });
    }

    public Future<Uint256> toTimestamp(Uint16 year, Uint8 month, Uint8 day, Uint8 hour, Uint8 minute, Uint8 second) {
        Function function = new Function("toTimestamp", 
                Arrays.<Type>asList(year, month, day, hour, minute, second), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Bool> isLeapYear(Uint16 year) {
        Function function = new Function("isLeapYear", 
                Arrays.<Type>asList(year), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> pendingWithdrawals(Address param0) {
        Function function = new Function("pendingWithdrawals", 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> __getResults(Uint256 matchId, Bool draw, Bool homeWon) {
        Function function = new Function("__getResults", Arrays.<Type>asList(matchId, draw, homeWon), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public static Future<SimpleBet> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(SimpleBet.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static Future<SimpleBet> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(SimpleBet.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static SimpleBet load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SimpleBet(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static SimpleBet load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SimpleBet(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class BetProposedEventResponse {
        public Address proposedBy;

        public Address against;

        public Uint256 betId;
    }

    public static class BetAcceptedEventResponse {
        public Uint256 betId;
    }

    public static class WithdrawalIncreasedEventResponse {
        public Address adr;

        public Uint256 value;
    }
}
