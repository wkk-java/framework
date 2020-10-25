<p>
	<table style="text-align:center;width:100%;" cellpadding="2" cellspacing="0" border="1" bordercolor="#000000">
		<tbody>
			<tr>
				<td style="text-align:center;" colspan="3">
					<h1 style="text-align:center;">
						framework (springcloud2.x版)
					</h1>
				</td>
			</tr>
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
				<td colspan="3" style="text-align:center;">
					<span style="font-size:18px;"><em><strong>插件相关依赖</strong></em></span> 
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
						google官方插件,与maven命令执行绑定,自动构建docker镜像并推送到docker私服.优点:
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
					1.0.0-SNAPSHOT
				</td>
				<td>
					<p>
						自定义插件,优点:与maven的执行绑定自动生成需要的容器编排yaml文件,便于更加快速智能的CI
					</p>
				</td>
			</tr>
		</tbody>
	</table>
</p>
