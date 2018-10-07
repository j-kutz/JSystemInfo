package application;

import org.hyperic.sigar.*;

public class SystemInfoCollector {
	private Sigar sigar;
	private Mem mem;
	private Uptime uptime;
	private CpuInfo cpuInfo;
	private String osName;
	private String osVersion;
	private String osArch;
	private String userName;
	private String memTotal;
	private String memUsed;
	private String procInfo;
	private String numCores;
	private String procVendor;
	private String uptimeFormatted;
	
	public SystemInfoCollector() throws SigarException {
		sigar = new Sigar();
		mem = new Mem();
		uptime = new Uptime();
		cpuInfo = new CpuInfo();
		
		mem.gather(sigar);
		uptime.gather(sigar);
		CpuInfo list[] = sigar.getCpuInfoList();
		cpuInfo = list[0];
		
		osName = System.getProperty("os.name");
		osVersion = System.getProperty("os.version");
		osArch = System.getProperty("os.arch");
		userName = System.getProperty("user.name");
		memTotal = Sigar.formatSize(mem.getTotal());
		memUsed = Sigar.formatSize(mem.getUsed());
		procInfo = cpuInfo.getModel();
		numCores = Integer.toString(cpuInfo.getTotalCores());
		procVendor = cpuInfo.getVendor();
		uptimeFormatted = timeFormatter(uptime.getUptime());
	}
	
	private String timeFormatter(double time) {
		int hours, minutes, seconds;
		seconds = (int) time;
		hours = seconds/3600;
		seconds -= (hours*3600);
		minutes = seconds/60;
		seconds -= (minutes*60);
		return hours + ":" + minutes + ":" + seconds;
	}
}
