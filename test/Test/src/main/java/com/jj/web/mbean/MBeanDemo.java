package com.jj.web.mbean;
import java.lang.management.ClassLoadingMXBean;
import java.lang.management.CompilationMXBean;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryManagerMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import java.util.List;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

public class MBeanDemo {

    public static void main(String[] args) {

        showJvmInfo();
        showMemoryInfo();
        showSystem();
        showClassLoading();
        showCompilation();
        showThread();
        showGarbageCollector();
        showMemoryManager();
        showMemoryPool();
//        MBeanDemo.visitMBean();
    }

    /**
     * Java ����������ʱϵͳ
     */
    public static void showJvmInfo() {
        RuntimeMXBean mxbean = ManagementFactory.getRuntimeMXBean();
        String vendor = mxbean.getVmVendor();
        System.out.println("jvm name:" + mxbean.getVmName());		//��������
        System.out.println("jvm vendor:" + vendor);					//JAVA��˾���
        System.out.println("jvm version:" + mxbean.getVmVersion());
        String clspaths = mxbean.getBootClassPath();
        String clspath [] = clspaths.split(";");
        System.out.println("jvm bootClassPath:");
        for(String path:clspath){
        	System.out.println("\t"+path);
        }
        System.out.println("jvm start time:" + mxbean.getStartTime());
    }

    /**
     * Java �������ڴ�ϵͳ
     */
    public static void showMemoryInfo() {
        MemoryMXBean mem = ManagementFactory.getMemoryMXBean();
        MemoryUsage heap = mem.getHeapMemoryUsage();
        System.out.println("Heap committed:\t" + heap.getCommitted() );
        System.out.println("Init:\t" + heap.getInit() );
        System.out.println("Max:\t" + heap.getMax());
        System.out.println("Used:\t" + heap.getUsed());
    }

    /**
     * Java ��������������еĲ���ϵͳ
     */
    public static void showSystem() {
        OperatingSystemMXBean op = ManagementFactory.getOperatingSystemMXBean();
        System.out.println("Architecture: \t" + op.getArch());
        System.out.println("Processors: \t" + op.getAvailableProcessors());
        System.out.println("System name: \t" + op.getName());
        System.out.println("System version: \t" + op.getVersion());
        System.out.println("Last minute load: \t" + op.getSystemLoadAverage());
    }
    
    /**
     * Java �����������ϵͳ
     */
    public static void showClassLoading(){
        ClassLoadingMXBean cl = ManagementFactory.getClassLoadingMXBean();
        System.out.println("TotalLoadedClassCount: \t" + cl.getTotalLoadedClassCount());
        System.out.println("LoadedClassCount:\t" + cl.getLoadedClassCount());
        System.out.println("UnloadedClassCount:\t" + cl.getUnloadedClassCount());
    }
    
    /**
     * Java �����ı���ϵͳ
     */
    public static void showCompilation(){
        CompilationMXBean com = ManagementFactory.getCompilationMXBean();
        System.out.println("TotalCompilationTime:" + com.getTotalCompilationTime());
        System.out.println("name:" + com.getName());
    }
    
    /**
     * Java �������߳�ϵͳ
     */
    public static void showThread(){
        ThreadMXBean thread = ManagementFactory.getThreadMXBean();
        System.out.println("ThreadCount:\t" + thread.getThreadCount());
        System.out.println("AllThreadIds:\t" +thread.getAllThreadIds());
//        long [] ids = thread.getAllThreadIds();
//        for(long id:ids){
//        	System.out.println("\t"+id);
//        }
        System.out.println("CurrentThreadUserTime:\t" + thread.getCurrentThreadUserTime());
        System.out.println("CurrentThreadCpuTime:\t"+thread.getCurrentThreadCpuTime());
        System.out.println("DaemonThreadCount:\t"+thread.getDaemonThreadCount());
        //......��������ܶ���Ϣ
    }
    
    /**
     * Java ������е������������
     */
    public static void showGarbageCollector(){
        List<GarbageCollectorMXBean> gc = ManagementFactory.getGarbageCollectorMXBeans();
        for(GarbageCollectorMXBean GarbageCollectorMXBean : gc){
            System.out.println("name:\t" + GarbageCollectorMXBean.getName()); 
            System.out.println("CollectionCount:\t" + GarbageCollectorMXBean.getCollectionCount());
            System.out.println("Collection Time:\t" + GarbageCollectorMXBean.getCollectionTime());  
        }
    }
    
    /**
     * Java ������е��ڴ������
     */
    public static void showMemoryManager(){
        List<MemoryManagerMXBean> mm = ManagementFactory.getMemoryManagerMXBeans();
        for(MemoryManagerMXBean eachmm: mm){
            System.out.println("Name:\t" + eachmm.getName());
            String [] pools = eachmm.getMemoryPoolNames();
            for(String pool:pools){
            	System.out.println("MemoryPoolNames:\t" + pool);
            }
        }
    }
    
    /**
     * Java ������е��ڴ��
     */
    public static void showMemoryPool(){
        List<MemoryPoolMXBean> mps = ManagementFactory.getMemoryPoolMXBeans();
        for(MemoryPoolMXBean mp : mps){
            System.out.println("Name:" + mp.getName());
            System.out.println("CollectionUsage:" + mp.getCollectionUsage());
            System.out.println("Type:" + mp.getType());
        }
    }
    
    /**
     * ���� MXBean �ķ��������ַ���
     */
    public static void visitMBean(){
        
        //��һ��ֱ�ӵ���ͬһ Java ������ڵ� MXBean �еķ�����
        RuntimeMXBean mxbean = ManagementFactory.getRuntimeMXBean();
        String vendor1 = mxbean.getVmVendor();
        System.out.println("vendor1:" + vendor1);
        
        //�ڶ���ͨ��һ�����ӵ��������е�������ƽ̨ MBeanServer �� MBeanServerConnection��
        MBeanServerConnection mbs = null;
        // Connect to a running JVM (or itself) and get MBeanServerConnection
        // that has the JVM MXBeans registered in it
        
        try {
            // Assuming the RuntimeMXBean has been registered in mbs
            ObjectName oname = new ObjectName(ManagementFactory.RUNTIME_MXBEAN_NAME);
            String vendor2 = (String) mbs.getAttribute(oname, "VmVendor");
            System.out.println("vendor2:" + vendor2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        //������ʹ�� MXBean ����
//        MBeanServerConnection mbs3 = null;
//        RuntimeMXBean proxy;
//        try {
//            proxy = ManagementFactory.newPlatformMXBeanProxy(mbs3,ManagementFactory.RUNTIME_MXBEAN_NAME,
//                                                     RuntimeMXBean.class);
//            String vendor = proxy.getVmVendor();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

}
