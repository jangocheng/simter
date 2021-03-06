package com.yinsin.simter.controller.shell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShellManager {
	
	/** userId, List<ShellInstance> */
	public static Map<String, List<ShellInstance>> shellMap = new HashMap<String, List<ShellInstance>>();
	
	public static void add(String userId, ShellInstance instance){
		List<ShellInstance> clientShells= shellMap.get(userId);
		if(null == clientShells){
			clientShells = new ArrayList<ShellInstance>();
			clientShells.add(instance);
		} else {
			ShellInstance old = getByIp(userId, instance.getServerIp());
			if(old == null){
				clientShells.add(instance);
			}
		}
		shellMap.put(userId, clientShells);
	}
	
	public static List<ShellInstance> get(String userId){
		return shellMap.get(userId);
	}
	
	public static ShellInstance get(String userId, String sid){
		List<ShellInstance> clientShells = shellMap.get(userId);
		if(null != clientShells){
			for(ShellInstance instance : clientShells){
				if(instance.getClient() != null && instance.getClient().getSid().equals(sid)){
					return instance;
				}
			}
		}
		return null;
	}
	
	public static ShellInstance getByIp(String userId, String serverIp){
		List<ShellInstance> clientShells = shellMap.get(userId);
		if(null != clientShells){
			for(ShellInstance instance : clientShells){
				if(instance.getServerIp() != null && instance.getServerIp().equals(serverIp)){
					return instance;
				}
			}
		}
		return null;
	}
	
	public static void reset(String userId, String sid){
		ShellInstance shell = get(userId, sid);
		if(shell != null){
			shell.setClient(null);
		}
	}
}
