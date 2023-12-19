<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/clientes">
        <html>
            <head>
                <link rel="stylesheet" href="../styles/style.css" />
                <title>Top ventas</title>
            </head>
            <body>
                <h2>Top Ventas</h2>
                <table>
                    <tr>
                        <th>Nombre</th>
                        <th>Facturacion</th>
                        <th>Pedidos</th>
                    </tr>
                    <xsl:for-each select="cliente">
                        <xsl:sort select="facturacion" order="descending" />
                        <xsl:if test="11>position()">
                            <tr>
                                <td>
                                    <xsl:value-of select="nombre" />
                                </td>
                                <td>
                                    <xsl:value-of select="facturacion" />
                                </td>
                                <td>
                                    <xsl:value-of select="pedidos" />
                                </td>
                            </tr>
                        </xsl:if>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>