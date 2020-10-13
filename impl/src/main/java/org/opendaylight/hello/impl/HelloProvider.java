/*
 * Copyright © 2018  Copyright (c) 2015 Yoyodyne, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.hello.impl;

import org.opendaylight.mdsal.binding.api.DataBroker;
import org.opendaylight.mdsal.binding.api.RpcProviderService;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.hello.rev191127.HelloService;
import org.opendaylight.yangtools.concepts.ObjectRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloProvider {

    private static final Logger LOG = LoggerFactory.getLogger(HelloProvider.class);

    private final DataBroker dataBroker;
    private ObjectRegistration<HelloService> helloService;
    private RpcProviderService rpcProviderService;

    public HelloProvider(final DataBroker dataBroker, final RpcProviderService rpcProviderService) {
        this.dataBroker = dataBroker;
        this.rpcProviderService = rpcProviderService;
    }

    /**
     * Method called when the blueprint container is created.
     */
    public void init() {
        LOG.info("HelloProvider Session Initiated");
        helloService = rpcProviderService.registerRpcImplementation(HelloService.class, new HelloWorldImpl());
    }

    /**
     * Method called when the blueprint container is destroyed.
     */
    public void close() {
        LOG.info("HelloProvider Closed");
        if (helloService != null) {
            helloService.close();
        }
    }
}