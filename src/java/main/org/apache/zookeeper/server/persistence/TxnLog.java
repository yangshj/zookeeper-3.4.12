/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.zookeeper.server.persistence;

import java.io.IOException;

import org.apache.jute.Record;
import org.apache.zookeeper.txn.TxnHeader;

/**
 * Interface for reading transaction logs.
 * 该组件提供事务日志的序列化和反序列化
 */
public interface TxnLog {
    
    /**
     * roll the current log being appended to
     * 滚动日志,创建新的日志文件
     * @throws IOException 
     */
    void rollLog() throws IOException;
    /**
     * Append a request to the transaction log
     * @param hdr the transaction header
     * @param r the transaction itself
     * returns true iff something appended, otw false 
     * @throws IOException
     */
    boolean append(TxnHeader hdr, Record r) throws IOException;

    /**
     * Start reading the transaction logs from a given zxid
     * 创建一个迭代器,初始指向指定的zxid对应的事务记录,可向后迭代遍历
     * @param zxid
     * @return returns an iterator to read the 
     * next transaction in the logs.
     * @throws IOException
     */
    TxnIterator read(long zxid) throws IOException;
    
    /**
     * the last zxid of the logged transactions.
     * 获取当前记录的事务中最大的zxid
     * @return the last zxid of the logged transactions.
     * @throws IOException
     */
    long getLastLoggedZxid() throws IOException;
    
    /**
     * truncate the log to get in sync with the leader.
     * 将指定zxid后面的事务数据全部从文件中删除
     * @param zxid the zxid to truncate at.
     * @throws IOException 
     */
    boolean truncate(long zxid) throws IOException;
    
    /**
     * the dbid for this transaction log. 
     * @return the dbid for this transaction log.
     * @throws IOException
     */
    long getDbId() throws IOException;
    
    /**
     * commmit the trasaction and make sure
     * they are persisted
     * @throws IOException
     */
    void commit() throws IOException;
   
    /** 
     * close the transactions logs
     */
    void close() throws IOException;
    /**
     * an iterating interface for reading 
     * transaction logs. 
     */
    public interface TxnIterator {
        /**
         * return the transaction header.
         * @return return the transaction header.
         */
        TxnHeader getHeader();
        
        /**
         * return the transaction record.
         * @return return the transaction record.
         */
        Record getTxn();
     
        /**
         * go to the next transaction record.
         * @throws IOException
         */
        boolean next() throws IOException;
        
        /**
         * close files and release the 
         * resources
         * @throws IOException
         */
        void close() throws IOException;
    }
}

