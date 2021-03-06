/**
 * Product Name : Modus TR-069 Orange
 *
 * Copyright c 2014 Orange
 *
 * This software is distributed under the Apache License, Version 2.0
 * (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 or see the "license.txt" file for
 * more details
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Author: Antonin Chazalet - Orange
 * Mail: antonin.chazalet@orange.com;antonin.chazale@gmail.com
 */

package com.francetelecom.admindm.sm_baseline1profile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Manager {

	private static Manager instance;

	/**
	 * Ordered list of DeploymentUnit, i.e. Bundle.
	 */
	private List deploymentUnits = new ArrayList();

	/**
	 * Ordered list of ExecutionUnit, i.e. Bundle.
	 */
	private List executionUnits = new ArrayList();

	private static final Object lock = new Object();
	private static final Object lock2 = new Object();

	/** Must be private, use getSingletonInstance(). */
	private Manager() {

	}

	/**
	 * @return the singleton instance of the class.
	 */
	public static Manager getSingletonInstance() {
		if (instance == null) {
			instance = new Manager();
		}
		return instance;
	}

	/**
	 * @return List of DeploymentUnit
	 */
	public List getDeploymentUnits() {
		synchronized (lock) {
			List copy = new ArrayList();
			for (int i = 0; i < this.deploymentUnits.size(); i = i + 1) {
				copy.add(this.deploymentUnits.get(i));
			}
			return copy;
		}
	}

	public void addADeploymentUnit(final DeploymentUnit du) {
		synchronized (lock) {
			for (Iterator it = deploymentUnits.iterator(); it.hasNext(); ) {
				if (((DeploymentUnit)it.next()).getUuid() == du.getUuid()) {
					return;
				}
			}
			this.deploymentUnits.add(du);
		}
	}

	public void removeADeploymentUnit(final DeploymentUnit du) {
		synchronized (lock) {
			this.deploymentUnits.remove(du);
		}
	}
	
	/**
	 * Get a DU by id
	 * @param duid deployment unit id
	 * @return a deployment unit or null
	 */
	public DeploymentUnit getDeploymentUnit(final long duid) {
		synchronized (lock) {
			for (Iterator it = deploymentUnits.iterator(); it.hasNext();) {
				DeploymentUnit du = (DeploymentUnit) it.next();
				if (du.getDuid() == duid) {
					return du;
				}
			}
		}
		return null;
	}

	/**
	 * @return List of ExecutionUnit
	 */
	public List getExecutionUnits() {
		synchronized (lock2) {
			List copy = new ArrayList();
			for (int i = 0; i < this.executionUnits.size(); i = i + 1) {
				copy.add(this.executionUnits.get(i));
			}
			return copy;
		}
	}

	public void addAnExecutionUnit(final ExecutionUnit eu) {
		synchronized (lock2) {
			for (Iterator it = executionUnits.iterator(); it.hasNext(); ) {
				if (((ExecutionUnit)it.next()).getEuid() == eu.getEuid()) {
					return;
				}
			}
			this.executionUnits.add(eu);
		}
	}

	public void removeAnExecutionUnit(final ExecutionUnit eu) {
		synchronized (lock2) {
			this.executionUnits.remove(eu);
		}
	}
	
	/**
	 * Get an execution unit by euid
	 * @param euid execution unit id
	 * @return the requested execution unit or null
	 */
	public ExecutionUnit getExecutionUnit(final long euid) {
		synchronized (lock2) {
			for (Iterator it = executionUnits.iterator(); it.hasNext();) {
				ExecutionUnit eu = (ExecutionUnit) it.next();
				if (eu.getEuid() == euid) {
					return eu;
				}
			}
		}
		return null;
	}

}
