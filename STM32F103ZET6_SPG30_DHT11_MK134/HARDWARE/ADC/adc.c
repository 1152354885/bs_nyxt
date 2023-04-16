#include "adc.h"
#include "delay.h"
#include "sys.h"

 

//初始化ADC3
//这里我们仅以规则通道为例
//我们默认仅开启通道6																	   
void  Adc3_Init(void)
{      
	ADC_InitTypeDef ADC_InitStructure;
	
	 //使能ADC3通道时钟
	RCC_APB2PeriphClockCmd(RCC_APB2Periph_ADC3,ENABLE);
  //分频   
	RCC_ADCCLKConfig(RCC_PCLK2_Div6);
	//复位ADC3,将外设 ADC3的全部寄存器重设为缺省值
	ADC_DeInit(ADC3);
	//ADC工作模式: 独立模式
  ADC_InitStructure.ADC_Mode = ADC_Mode_Independent;
	//模数转换工作在单通道模式
	ADC_InitStructure.ADC_ScanConvMode = DISABLE;
		//模数转换工作在单次转换模式
  ADC_InitStructure.ADC_ContinuousConvMode = DISABLE;
		//转换由软件而不是外部触发启动
	ADC_InitStructure.ADC_ExternalTrigConv = ADC_ExternalTrigConv_None;
	//ADC数据右对齐
	ADC_InitStructure.ADC_DataAlign = ADC_DataAlign_Right;
	//顺序进行规则转换的ADC通道的数目
	ADC_InitStructure.ADC_NbrOfChannel = 1;
			//根据ADC_InitStruct中指定的参数初始化外设ADCx的寄存器  
	ADC_Init(ADC3,&ADC_InitStructure);
	//使能指定的ADC3
	ADC_Cmd(ADC3,ENABLE);
	//使能复位校准  
	ADC_ResetCalibration(ADC3);
	//等待复位校准结束
	while(ADC_GetResetCalibrationStatus(ADC3));
//启动校准
	ADC_StartCalibration(ADC3);
		 //等待校准结束
	while(ADC_GetCalibrationStatus(ADC3));
}	

//获得ADC3某个通道的值
//ch:通道值 0~16
//返回值:转换结果
u16 Get_Adc3(u8 ch)   
{
  //设置指定ADC的规则组通道，一个序列，采样时间
	//ADC3,ADC通道,采样时间为239.5周期	  			    
	ADC_RegularChannelConfig(ADC3, ch, 1, ADC_SampleTime_239Cycles5 );
	//使能指定的ADC3的软件转换启动功能	
	ADC_SoftwareStartConvCmd(ADC3,ENABLE);
	//等待转换结束
	while(!ADC_GetFlagStatus(ADC3,ADC_FLAG_EOC));
	//返回最近一次ADC3规则组的转换结果
	return ADC_GetConversionValue(ADC3);
} 
