<h1>
	<span style="line-height:1;">framework (springcloud2.x版)
	<hr />
	</span> 
</h1>
<p>
	<table style="width:100%;" cellpadding="2" cellspacing="0" border="1" bordercolor="#000000">
		<tbody>
			<tr>
				<td style="text-align:center;">
					<p style="text-align:center;">
						依赖
					</p>
				</td>
				<td style="text-align:center;">
					版本<br />
				</td>
				<td style="text-align:center;">
					说明
				</td>
			</tr>
			<tr>
				<td style="text-align:center;">
					<p style="text-align:left;">
						<span>jdk</span> 
					</p>
				</td>
				<td style="text-align:left;">
					1.8<br />
				</td>
				<td style="text-align:left;">
					<br />
				</td>
			</tr>
			<tr>
				<td>
					SpringCloud<br />
				</td>
				<td>
					GreenWich.SR6<br />
				</td>
				<td>
					<br />
				</td>
			</tr>
			<tr>
				<td>
					Springboot
				</td>
				<td>
					2.1.12.RELEASE
				</td>
				<td>
				</td>
			</tr>
			<tr>
				<td>
					<br />
				</td>
				<td>
					1.8
				</td>
				<td>
				</td>
			</tr>
			<tr>
				<td>
					jib-maven-plugin
				</td>
				<td>
<pre>2.6.0</pre>
				</td>
				<td>
					<p>
						与maven命令执行绑定,自动构建docker镜像并推送到docker私服.优点:
					</p>
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1.本地无需docker daemon后台程序&nbsp;
					</p>
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.增量打包(仅构建改动部分,更快速的CI)
					</p>
				</td>
			</tr>
			<tr>
				<td>
					dockercompose-maven-plugin
				</td>
				<td>
				</td>
				<td>
					<p>
						自定义插件优点:与maven的执行绑定自动生成需要的容器编排yaml文件,便于更加快速智能的CI
					</p>
				</td>
			</tr>
		</tbody>
	</table>
</p>
<p>
	<br />
</p>
