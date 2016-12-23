/*******************************************************************************
 * Copyright (c) 2011, 2016 Eurotech and/or its affiliates and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eurotech - initial API and implementation
 *
 *******************************************************************************/
package org.eclipse.kapua.service.authorization.group.shiro;

import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.commons.jpa.EntityManager;
import org.eclipse.kapua.commons.service.internal.ServiceDAO;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.model.query.KapuaQuery;
import org.eclipse.kapua.service.authorization.group.Group;
import org.eclipse.kapua.service.authorization.group.GroupCreator;
import org.eclipse.kapua.service.authorization.group.GroupListResult;
import org.eclipse.kapua.service.authorization.group.GroupQuery;

/**
 * {@link Group} DAO
 * 
 * @since 1.0.0
 */
public class GroupDAO extends ServiceDAO {

    /**
     * Creates and returns new {@link Group}
     * 
     * @param em
     *            The {@link EntityManager} that holds the transaction.
     * @param creator
     *            The {@link GroupCreator} object from which create the new {@link Group}.
     * @return The newly created {@link Group}.
     * @throws KapuaException
     * @since 1.0.0
     */
    public static Group create(EntityManager em, GroupCreator creator)
            throws KapuaException {
        Group group = new GroupImpl(creator.getScopeId());
        group.setName(creator.getName());

        return ServiceDAO.create(em, group);
    }

    /**
     * Updates and returns the updated {@link Group}
     * 
     * @param em
     *            The {@link EntityManager} that holds the transaction.
     * @param group
     *            The {@link Group} to update
     * @return The updated {@link Group}.
     */
    public static Group update(EntityManager em, Group group) {
        GroupImpl groupImpl = (GroupImpl) group;
        return ServiceDAO.update(em, GroupImpl.class, groupImpl);
    }

    /**
     * Finds the {@link Group} by {@link Group} identifier
     * 
     * @param em
     *            The {@link EntityManager} that holds the transaction.
     * @param groupId
     *            The {@link Group} id to search.
     * @return The found {@link Group} or {@code null} if not found.
     * @since 1.0.0
     */
    public static Group find(EntityManager em, KapuaId groupId) {
        return em.find(GroupImpl.class, groupId);
    }

    /**
     * Deletes the {@link Group} by {@link Group} identifier
     * 
     * @param em
     *            The {@link EntityManager} that holds the transaction.
     * @param groupId
     *            The {@link Group} id to delete.
     * @since 1.0.0
     */
    public static void delete(EntityManager em, KapuaId groupId) {
        ServiceDAO.delete(em, GroupImpl.class, groupId);
    }

    /**
     * Returns the {@link Group} list matching the provided query.
     * 
     * @param em
     *            The {@link EntityManager} that holds the transaction.
     * @param groupQuery
     *            The {@link GroupQuery} used to filter results.
     * @return The list of {@link Group}s that matches the given query.
     * @throws KapuaException
     * @since 1.0.0
     */
    public static GroupListResult query(EntityManager em, KapuaQuery<Group> groupQuery)
            throws KapuaException {
        return ServiceDAO.query(em, Group.class, GroupImpl.class, new GroupListResultImpl(), groupQuery);
    }

    /**
     * Return the {@link Group} count matching the provided query
     * 
     * @param em
     *            The {@link EntityManager} that holds the transaction.
     * @param groupQuery
     *            The {@link GroupQuery} used to filter results.
     * @return
     * @throws KapuaException
     * @since 1.0.0
     */
    public static long count(EntityManager em, KapuaQuery<Group> groupQuery)
            throws KapuaException {
        return ServiceDAO.count(em, Group.class, GroupImpl.class, groupQuery);
    }
}
